<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入文章标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文章作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入文章作者"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="publish">
        <el-select v-model="queryParams.publish" placeholder="请选择发布状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
          v-hasPermi="['admin:article:add']"
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
          v-hasPermi="['admin:article:edit']"
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
          v-hasPermi="['admin:article:remove']"
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
          v-hasPermi="['admin:article:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" width="50" align="center" prop="articleId" />
      <el-table-column label="标题" width="300" align="center" prop="title" />
      <el-table-column label="分类" width="250" align="center" prop="category.name" />
      <el-table-column label="标签" width="300" align="center" prop="tags" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-row>
            <el-tag style="margin-right: 2px" v-for="tag in scope.row.tags" :key="tag.id" size="mini">{{ tag.name }}</el-tag>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column label="作者" width="80" align="center" prop="author" />
      <el-table-column label="阅读量" width="80" align="center" prop="readNum" />
      <el-table-column label="评论量" width="80" align="center" prop="commentNum" />
      <el-table-column label="点赞量" width="80" align="center" prop="likeNum" />
      <el-table-column label="推荐" align="center">
        <template slot-scope="scope" prop="isRecommend">
          <el-switch
            :active-value="0" :inactive-value="1"
            v-model="scope.row.isRecommend"
            @change="handleRecommend(scope.row)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="置顶" align="center" prop="isTop">
        <template slot-scope="scope">
          <el-switch
            :active-value="1" :inactive-value="0"
            v-model="scope.row.isTop"
            @change="handleTop(scope.row)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="状态" header-align="center" align="center" prop="recommend">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="点击发布" v-if="scope.row.publish === 1" placement="top">
            <el-button type="info" size="mini" @click="handlePublish(scope.row)">未发布</el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="点击下架" v-if="scope.row.publish === 0" placement="top">
            <el-button type="success" size="mini" @click="handlePublish(scope.row)"
                       v-if="scope.row.publish === 0">已发布
            </el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" width="130" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['admin:article:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['admin:article:remove']"
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
  </div>
</template>

<script>
import { listArticle, delArticle, exportArticle, updateArticle } from '@/api/admin/article'
import { getDicts } from '@/api/system/dict/data'

export default {
  name: "Article",
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
      // 文章表格数据
      articleList: [],
      // 状态数据字典
      statusOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        author: null,
        publish: null,
      },
    };
  },
  created() {
    this.getList();
    this.getDicts('admin_article_status').then(response => {
      this.statusOptions = response.data
    })
  },
  methods: {
    /** 查询文章列表 */
    getList() {
      this.loading = true;
      listArticle(this.queryParams).then(response => {
        this.articleList = response.data.data;
        this.total = response.data.total;
        this.loading = false;
      });
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
      this.ids = selection.map(item => item.articleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.$router.push('/blog/add');
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const articleId = row.articleId || this.ids
      this.$router.push('/blog/update/'+articleId)
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const articleIds = row.articleId || this.ids;
      this.$confirm('是否确认删除文章编号为"' + articleIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delArticle(articleIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有文章数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportArticle(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {});
    },
    /** 推荐操作 */
    handleRecommend(row) {
      let text = row.isRecommend === '0' ? '推荐' : '不推荐'
      this.$confirm('确认' + text + '"' + row.title + '"博文吗?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        let data = {
          articleId: row.articleId,
          isRecommend: row.isRecommend
        }
        return updateArticle(data)
      }).then(() => {
        this.msgSuccess(text + '成功')
      }).catch(function() {
        row.isRecommend = row.isRecommend === '0' ? '0' : '1'
      })
    },
    /** 置顶操作 */
    handleTop(row) {
      let text = row.isTop === '0' ? '置顶' : '不置顶'
      this.$confirm('确认要"' + text + '""' + row.title + '"博文吗?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        let data = {
          articleId: row.articleId,
          isTop: row.isTop
        }
        return updateArticle(data)
      }).then(() => {
        this.msgSuccess(text + '成功')
      }).catch(function() {
        row.isTop = row.isTop === '0' ? '0' : '1'
      })
    },
    /** 发布状态 */
    handlePublish (row) {
      let text = row.publish === '0' ? '发布' : '下架'
      this.$confirm('确认要"' + text + '""' + row.title + '"博文吗?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        let data = {
          articleId: row.articleId,
          publish: row.publish
        }
        return updateArticle(data)
      }).then(() => {
        this.msgSuccess(text + '成功')
      }).catch(function() {
        row.isTop = row.isTop === '0' ? '1' : '0'
      })
    },
  }
};
</script>
