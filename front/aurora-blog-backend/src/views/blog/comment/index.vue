<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="评论类型：0:留言 1:文章" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择评论类型：0:留言 1:文章" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="被评论id，可以是单个文章id、项目、资源" prop="ownerId">
        <el-input
          v-model="queryParams.ownerId"
          placeholder="请输入被评论id，可以是单个文章id、项目、资源"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论id 第一级为0" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入评论id 第一级为0"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论者名字" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入评论者名字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="点赞的数量" prop="likeNum">
        <el-input
          v-model="queryParams.likeNum"
          placeholder="请输入点赞的数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="踩的数量" prop="dislikeNum">
        <el-input
          v-model="queryParams.dislikeNum"
          placeholder="请输入踩的数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="回复的id" prop="replyId">
        <el-input
          v-model="queryParams.replyId"
          placeholder="请输入回复的id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="回复评论者名字" prop="replyName">
        <el-input
          v-model="queryParams.replyName"
          placeholder="请输入回复评论者名字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['admin:comment:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:comment:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:comment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['admin:comment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="commentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评论主键id" align="center" prop="id" />
      <el-table-column label="评论类型：0:留言 1:文章" align="center" prop="type" />
      <el-table-column label="被评论id，可以是单个文章id、项目、资源" align="center" prop="ownerId" />
      <el-table-column label="评论id 第一级为0" align="center" prop="parentId" />
      <el-table-column label="评论者名字" align="center" prop="name" />
      <el-table-column label="评论者头像" align="center" prop="avatar" />
      <el-table-column label="点赞的数量" align="center" prop="likeNum" />
      <el-table-column label="踩的数量" align="center" prop="dislikeNum" />
      <el-table-column label="评论内容" align="center" prop="content" />
      <el-table-column label="回复的id" align="center" prop="replyId" />
      <el-table-column label="回复评论者名字" align="center" prop="replyName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:comment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:comment:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改评论对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="评论类型：0:留言 1:文章" prop="type">
          <el-select v-model="form.type" placeholder="请选择评论类型：0:留言 1:文章">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="被评论id，可以是单个文章id、项目、资源" prop="ownerId">
          <el-input v-model="form.ownerId" placeholder="请输入被评论id，可以是单个文章id、项目、资源" />
        </el-form-item>
        <el-form-item label="评论id 第一级为0" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入评论id 第一级为0" />
        </el-form-item>
        <el-form-item label="评论者名字" prop="name">
          <el-input v-model="form.name" placeholder="请输入评论者名字" />
        </el-form-item>
        <el-form-item label="评论者头像" prop="avatar">
          <el-input v-model="form.avatar" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="点赞的数量" prop="likeNum">
          <el-input v-model="form.likeNum" placeholder="请输入点赞的数量" />
        </el-form-item>
        <el-form-item label="踩的数量" prop="dislikeNum">
          <el-input v-model="form.dislikeNum" placeholder="请输入踩的数量" />
        </el-form-item>
        <el-form-item label="评论内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="回复的id" prop="replyId">
          <el-input v-model="form.replyId" placeholder="请输入回复的id" />
        </el-form-item>
        <el-form-item label="回复评论者名字" prop="replyName">
          <el-input v-model="form.replyName" placeholder="请输入回复评论者名字" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listComment, getComment, delComment, addComment, updateComment, exportComment } from "@/api/admin/comment";

export default {
  name: "Comment",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 评论表格数据
      commentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        ownerId: null,
        parentId: null,
        name: null,
        avatar: null,
        likeNum: null,
        dislikeNum: null,
        content: null,
        replyId: null,
        replyName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [
          { required: true, message: "评论类型：0:留言 1:文章不能为空", trigger: "change" }
        ],
        ownerId: [
          { required: true, message: "被评论id，可以是单个文章id、项目、资源不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "评论者名字不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询评论列表 */
    getList() {
      this.loading = true;
      listComment(this.queryParams).then(response => {
        this.commentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        type: null,
        ownerId: null,
        parentId: null,
        name: null,
        avatar: null,
        likeNum: null,
        dislikeNum: null,
        content: null,
        replyId: null,
        replyName: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加评论";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getComment(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改评论";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateComment(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addComment(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除评论编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delComment(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有评论数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportComment(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {});
    }
  }
};
</script>
