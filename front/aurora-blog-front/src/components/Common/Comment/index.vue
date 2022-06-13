<!--评论模块-->
<template>
  <div class="container">
    <div class="comment-input">
      <div class="input-wrapper">
        <el-input class="gray-bg-input"
                  v-model="pComment"
                  type="textarea"
                  :rows="3"
                  autofocus
                  placeholder="写下你的评论">
        </el-input>
        <div class="btn-control">
          <el-button class="btn" type="primary" round @click="commitComment(0)">确定</el-button>
        </div>
      </div>
    </div>
    <div class="comment" v-for="item in comments" :key="item.commentId">
      <div class="info">
        <img class="avatar" :src="item.avatar" width="36" height="36"/>
        <div class="right">
          <div class="name">{{ item.name }}</div>
          <div class="date">{{ item.createTime }}</div>
        </div>
      </div>
      <div class="content">{{ item.content }}</div>
      <div class="control">
        <span class="like" :class="{active: item.isLike}" @click="likeClick(item)">
          <i class="iconfont icon-good"></i>
          <span class="like-num">{{ item.likeNum > 0 ? item.likeNum + '人赞' : '赞' }}</span>
        </span>
        <span class="like" :class="{active: item.isdisLike}" @click="dislikeClick(item)">
          <i class="iconfont icon-bad"></i>
          <span class="like-num">{{ item.dislikeNum > 0 ? item.dislikeNum + '人踩' : '踩' }}</span>
        </span>
        <span class="comment-reply" @click="showCommentInput(item)">
          <i class="iconfont icon-huifu"></i>
          <span>回复</span>
        </span>
      </div>
      <div class="reply">
        <div class="item" v-for="reply in item.reply" :key="reply.commentId">
          <div class="reply-content">
            <img style=" border-radius: 50%;" :src="reply.avatar" width="36" height="36"/>
            <span class="from-name">{{ reply.name }}</span><span>: </span>
            <span class="to-name">@{{ reply.replyName }}</span>
            <span>{{ reply.content }}</span>
          </div>
          <div class="reply-bottom">
            <span>{{ reply.createTime }}</span>
            <span class="like" :class="{active: reply.isLike}" @click="likeClick(reply)">
          <i class="iconfont icon-good"></i>
          <span class="like-num">{{ reply.likeNum > 0 ? reply.likeNum + '人赞' : '赞' }}</span>
        </span>
            <span class="like" :class="{active: reply.isdisLike}" @click="dislikeClick(reply)">
          <i class="iconfont icon-bad"></i>
          <span class="like-num">{{ reply.dislikeNum > 0 ? reply.dislikeNum + '人踩' : '踩' }}</span>
        </span>
            <span class="reply-text" @click="showCommentInput(item, reply)">
              <i class="iconfont icon-huifu"></i>
              <span>回复</span>
            </span>
          </div>
        </div>
        <!--        <div class="write-reply" v-if="item.reply.length > 0" @click="showCommentInput(item)">-->
        <!--          <i class="el-icon-edit"></i>-->
        <!--          <span class="add-comment">添加新评论</span>-->
        <!--        </div>-->
        <transition name="fade">
          <div class="input-wrapper" v-if="showItemId === item.commentId">
            <el-input class="gray-bg-input"
                      v-model="replyComment"
                      type="textarea"
                      :rows="3"
                      autofocus
                      placeholder="写下你的评论">
            </el-input>
            <div class="btn-control">
              <el-button class="cancel" type="primary" round @click="cancel">取消</el-button>
              <el-button class="btn" type="primary" round @click="commitComment(item.commentId)">确定</el-button>
            </div>
          </div>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>

import Vue from 'vue';
import {addComment} from '../../../api/all';

export default {
  props: {
    comments: {
      type: Array,
      required: true
    },
    commentType: {
      type: Number,
      required: true
    },
    articleId: {
      type: Number,
      required: false
    }
  },
  components: {},
  data() {
    return {
      pComment: '',
      replyComment: '',
      showItemId: '',
      comment: {
        type: this.commentType,
        ownerId: '',
        parentId: '',
        // 评论者id
        observerId: '',
        content: '',
        // 回复评论的id
        replyId: 0
      }
    }
  },
  computed: {},
  methods: {
    /**
     * 点赞
     */
    likeClick(item) {
      if (item.isLike === null) {
        Vue.$set(item, "isLike", true);
        item.likeNum++
      } else {
        if (item.isLike) {
          item.likeNum--
        } else {
          item.likeNum++
        }
        item.isLike = !item.isLike;
      }
    },
    /**
     * 踩
     */
    dislikeClick(item) {
      if (item.isdisLike === null) {
        Vue.$set(item, "isdisLike", true);
        item.dislikeNum++
      } else {
        if (item.isdisLike) {
          item.dislikeNum--
        } else {
          item.dislikeNum++
        }
        item.isdisLike = !item.isdisLike;
      }
    },

    /**
     * 点击取消按钮
     */
    cancel() {
      this.showItemId = ''
    },

    /**
     * 提交评论
     */
    commitComment(itemId) {
      const userId = localStorage.getItem("userId");
      if (!userId) {
        this.$notify({
          title: '提示',
          message: '请先登录再评论',
          type: 'warning'
        });
        return;
      }
      this.comment.observerId = userId;
      this.comment.parentId = itemId;
      this.comment.content = this.replyComment;
      this.comment.ownerId = this.articleId;
      if (itemId === 0) {
        this.comment.content = this.pComment;
      }
      // 留言
      if (this.commentType === 0){
        this.comment.ownerId = 0
      }
      addComment(this.comment).then(res => {
        if (res.data.code === 200){
          location.reload();
        }
      })
    },

    /**
     * 点击评论按钮显示输入框
     * item: 当前大评论
     * reply: 当前回复的评论
     */
    showCommentInput(item, reply) {
      if (reply) {
        this.replyComment = "@" + reply.name + " "
        this.comment.replyId = reply.commentId;
      } else {
        this.replyComment = "@" + item.name + " "
        this.comment.replyId = item.commentId;
      }
      this.showItemId = item.commentId
    }
  },
  created() {
  }
}
</script>

<style>
.container {
  padding: 0 10px;
  box-sizing: border-box;
}

.container .comment-input .btn-control {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding-top: 10px;
  padding-bottom: 10px;
}

.container .comment {
  display: flex;
  flex-direction: column;
  padding: 10px 10px 0px 10px;
  border: 1px solid #F2F6FC;
}

.container .comment .info {
  display: flex;
  align-items: center;
}

.container .comment .info .avatar {
  border-radius: 50%;
}

.container .comment .info .right {
  display: flex;
  flex-direction: column;
  margin-left: 10px;
}

.container .comment .info .right .name {
  text-align: left;
  font-size: 16px;
  color: #303133;
  margin-bottom: 5px;
  font-weight: 500;
}

.container .comment .info .right .date {
  font-size: 12px;
  color: #909399;
}

.container .comment .content {
  text-align: left;
  font-size: 16px;
  color: #303133;
  line-height: 20px;
  padding: 10px 0;
}

.container .comment .control {
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #909399;
}

.container .comment .control .like {
  display: flex;
  align-items: center;
  margin-right: 10px;
  cursor: pointer;
}

.container .comment .control .like.active, .container .comment .control .like:hover {
  color: #409EFF;
}

.container .comment .control .like .iconfont {
  font-size: 14px;
  margin-right: 5px;
}

.container .comment .control .comment-reply {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.container .comment .control .comment-reply:hover {
  color: #409EFF;
}

.container .comment .control .comment-reply .iconfont {
  font-size: 16px;
  margin-right: 5px;
}

.container .comment .reply {
  margin: 10px 0;
  /*border-left: 2px solid #DCDFE6;*/
}

.container .comment .reply .item {
  margin: 0 10px;
  padding: 10px 0;
  /*border-bottom: 1px dashed #EBEEF5;*/
}

.container .comment .reply .item .like {
  display: flex;
  align-items: center;
  margin-left: 10px;
  cursor: pointer;
}

.container .comment .reply .item .like.active, .container .comment .reply .item .like:hover {
  color: #409EFF;
}

.container .comment .reply .item .like .iconfont {
  font-size: 14px;
  margin-right: 5px;
}

.container .comment .reply .item .reply-content {
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #303133;
}

.container .comment .reply .item .reply-content .from-name {
  margin-left: 5px;
  color: #303133;
}

.container .comment .reply .item .reply-content .to-name {
  color: #409EFF;
  margin-left: 5px;
  margin-right: 5px;
}

.container .comment .reply .item .reply-bottom {
  display: flex;
  align-items: center;
  margin-top: 6px;
  font-size: 12px;
  color: #909399;
}

.container .comment .reply .item .reply-bottom .reply-text {
  display: flex;
  align-items: center;
  margin-left: 10px;
  cursor: pointer;
}

.container .comment .reply .item .reply-bottom .reply-text:hover {
  color: #409EFF;
}

.container .comment .reply .item .reply-bottom .reply-text .icon-comment {
  margin-right: 5px;
}

.container .comment .reply .write-reply {
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #909399;
  padding: 10px;
  cursor: pointer;
}

.container .comment .reply .write-reply:hover {
  color: #409EFF;
}

.container .comment .reply .write-reply .el-icon-edit {
  margin-right: 5px;
}

.container .comment .reply .fade-enter-active, .container .comment .reply fade-leave-active {
  transition: opacity 0.5s;
}

.container .comment .reply .fade-enter, .container .comment .reply .fade-leave-to {
  opacity: 0;
}

.container .comment .reply .input-wrapper {
  padding: 10px;
}

.container .comment .reply .input-wrapper .gray-bg-input, .container .comment .reply .input-wrapper .el-input__inner {
  /*background-color: #67C23A;*/
}

.container .comment .reply .input-wrapper .btn-control {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding-top: 10px;
}

/*.container .comment .reply .input-wrapper .btn-control .cancel {*/
/*    font-size: 16px;*/
/*    color: #606266;*/
/*    margin-right: 20px;*/
/*    cursor: pointer;*/
/*}*/
/*.container .comment .reply .input-wrapper .btn-control .cancel:hover {*/
/*    color: #333;*/
/*}*/
.container .comment .reply .input-wrapper .btn-control .confirm {
  font-size: 16px;
}
</style>
