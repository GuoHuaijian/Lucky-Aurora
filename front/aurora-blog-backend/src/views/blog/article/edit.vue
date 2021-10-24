<template>
  <div style="margin-top: 25px">
    <el-form :model="article" label-width="80px" :rules="rules" ref="articleForm">
      <el-row>
        <el-col :span="12">
          <el-form-item label="博文标题" prop="title">
            <el-col :span="12">
              <el-input placeholder="博文标题" v-model="article.title" clearable></el-input>
            </el-col>
          </el-form-item>
          <el-row>
            <el-col :span="7">
              <el-form-item label="博文分类" prop="categorySelect">
                <el-select v-model="categorySelect" placeholder="请选择文章分类">
                  <el-option
                    v-for="item in category"
                    :key="item.categoryId"
                    :label="item.name"
                    :value="item.categoryId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="博文标签" prop="tagListSelect">
                <el-select
                  style="width: 100%"
                  v-model="tagListSelect"
                  multiple
                  allow-create
                  filterable
                  default-first-option
                  value-key="tagId"
                  name="name"
                  placeholder="请选择文章标签" @change="filterTagList"
                >
                  <el-option
                    v-for="item in tags"
                    :key="item.tagId"
                    :label="item.name"
                    :value="item.tagId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="博文作者" prop="author">
                <el-input placeholder="博文作者" v-model="article.author" clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="是否推荐" prop="recommend">
                <el-radio-group v-model="article.isRecommend">
                  <el-radio :label="true">是</el-radio>
                  <el-radio :label="false">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="展示类型" prop="coverType">
                <el-radio-group v-model="article.coverType">
                  <el-radio v-for="type in coverTypeList" :key="type.code" :label="type.code">{{ type.value }}
                  </el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="博文描述" prop="description">
            <el-col :span="12">
              <el-input type="textarea" v-model="article.description" placeholder="博文描述" clearable></el-input>
            </el-col>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="上传封面" prop="coverUrl">
            <el-col :span="12">
              <el-upload
                drag
                action="/dev-api/file/upload"
                list-type="picture"
                :multiple="false"
                :before-upload="beforeUploadHandle"
                :file-list="file"
                :headers="headers"
                :on-remove="handleRemove"
                :on-success="successHandle"
              >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只支持jpg、png、gif格式的图片！</div>
              </el-upload>
            </el-col>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="博文内容" prop="content">
        <mavon-editor ref="md" v-model="article.content" @imgAdd="imgAdd" @change="mavonChangeHandle"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="editArticle()">保存</el-button>
        <el-button @click="resetForm('articleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import MavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// import { treeDataTranslate } from '@/utils'
import marked from 'marked'
import { addArticle, getArticle, uploadFile, updateArticle } from '@/api/admin/article'
import { categorySelect } from '@/api/admin/category'
import { tagSelect } from '@/api/admin/tag'
import { getToken } from '@/utils/auth'
import store from '@/store'

export default {
  components: {
    'mavon-editor': MavonEditor.mavonEditor
  },
  data() {
    return {
      headers: {
        Authorization: 'Bearer ' + getToken()
      },
      article: {
        articleId: null,
        isRecommend: false,
        tags: [],
        type: 0,
        coverType: 2, // 默认无图片
        categoryId: '',
        author:''
      },
      coverTypeList: [{ code: 0, value: '小图片' }, { code: 1, value: '大图片' }, { code: 2, value: '无图片' }],
      url: '',
      file: [],
      rules: {
        title: { required: true, message: '请输入博文标题', trigger: 'change' },
        // categorySelect: { required: true, message: '请选择博文分类', trigger: 'blur' },
        // tagListSelect: { required: true, message: '请选择博文标签', trigger: 'blur' },
        author: { required: false },
        coverType: { required: false },
        description: { required: false },
        recommend: { required: false },
        coverUrl: { required: false },
        content: { required: true, message: '请输入博文内容', trigger: 'change' }
      },
      tags: [],
      tagListSelect: [],
      category: [],
      categorySelect: ''
    }
  },
  created() {
    this.article.author = store.getters.name
    this.init()
  },
  methods: {
    init() {
      let id = this.$route.params.id
      // 修改
      if (id) {
        tagSelect().then(res => {
          this.tags = res.data
        })
        categorySelect().then(res => {
          this.category = res.data
        })
        getArticle(id).then(res => {
          this.article = res.data
          this.article.coverType = res.data.coverType
          this.article.author = res.data.author
          if (res.data.tags) {
            this.tagListSelect = res.data.tags.map(tag => {
              return tag.tagId
            })
          }
          this.categorySelect = res.data.category.categoryId
          if (res.data.coverUrl) {
            this.file = [{ name: '封面', url: res.data.coverUrl }]
          }
        })
        // 添加
      } else {
        categorySelect().then(res => {
          this.category = res.data
        })
        tagSelect().then(res => {
          this.tags = res.data
        })
      }
    },
    // 过滤标签
    filterTagList(selectValueList) {
      let tagList = []
      selectValueList.forEach(value => {
        let isInput = true
        for (let i = 0; i < this.tags.length; i++) {
          let tag = this.tags[i]
          if (tag.tagId === value || value.tagId) {
            isInput = false
            tagList.push({ tagId: tag.tagId, name: tag.name })
          }
        }
        if (isInput) {
          tagList.push({ name: value })
        }
      })
      this.article.tags = tagList
    },
    // 上传之前
    beforeUploadHandle(file) {
      if (file.type !== 'image/jpg' && file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
        this.msgError('只支持jpg、png、gif格式的图片！')
        return false
      }
    },
    // 上传成功
    successHandle(response) {
      if (response && response.code === 200) {
        this.article.coverUrl = response.data.url
        this.file = [response.data]
        this.msgSuccess('上传成功！')
      }
    },
    // 移除上传文件
    handleRemove(file, fileList) {
      this.file = []
      this.article.coverUrl = ''
    },
    // 编辑文章
    editArticle() {
      this.$refs['articleForm'].validate((valid) => {
        if (valid) {
          // 转化categoryId
          this.article.categoryId = this.categorySelect
          if (this.article.articleId) {
            updateArticle(this.article).then(res => {
              if (res.code == 200) {
                this.msgSuccess('修改成功')
              } else {
                this.msgError('修改失败')
              }
            })
          } else {
            addArticle(this.article).then(res => {
              if (res.code == 200) {
                this.msgSuccess('保存成功')
              } else {
                this.msgError('保存失败')
              }
            })
          }
        } else {
          return false
        }
      })
    },
    // 文章内容图片上传
    imgAdd(pos, $file) {
      // 第一步.将图片上传到服务器.
      let formData = new FormData()
      formData.append('file', $file)
      uploadFile(formData).then(({ data }) => {
        this.$refs.md.$img2Url(pos, data.url)
      })
    },
    mavonChangeHandle(context, render) {
      this.article.contentFormat = marked(context)
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
