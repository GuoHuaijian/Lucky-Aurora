<template>
  <div class="article-list-year-title" :class="styleType">
    <span class="date"><i :class="iconType"></i></span>
    <span class="main-title">{{ date }}</span>
    <span class="vertical-line"></span>
    <span class="sub-title">共 <a>{{ count }}</a> 篇</span>
  </div>
</template>

<script>
export default {
  props: {
    dateType: {
      default: 'year'
    },
    // eslint-disable-next-line vue/require-prop-type-constructor
    date: '',
    // eslint-disable-next-line vue/require-prop-type-constructor
    count: '',
    tipText: {
      default: 'View All'
    },
    // eslint-disable-next-line vue/require-prop-type-constructor
    hrefText: ''
  },
  computed: {
    styleType() {
      return 'style-date-' + this.dateType
    },
    iconType() {
      return {
        'iconfont icon-nian': this.dateType === 'year',
        'iconfont icon-yuebao': this.dateType === 'month'
      }
    }
  }
}
</script>

<style>
.article-list-year-title {
  position: relative;
  text-align: left;
}

.article-list-year-title.style-date-year {
  padding: 15px 0;
  line-height: 50px;
}

@media only screen and (max-width: 720px) {
  .article-list-year-title.style-date-year {
    padding: 8px 0;
  }
}

.article-list-year-title.style-date-year .date {
  display: inline-block;
  font-size: 40px;
  vertical-align: middle;
}

.article-list-year-title.style-date-year .date .iconfont {
  font-size: 40px;
  color: #53a8ff;
}

.article-list-year-title.style-date-month .date .iconfont {
  font-size: 24px;
  color: #53a8ff;
}

.article-list-year-title.style-date-year .date::before {
  content: '';
  position: absolute;
  top: 0px;
  left: 17px;
  width: 4px;
  background-color: #409EFF;
  height: 20%;
  border-radius: 0 0 2px 2px;
}

.article-list-year-title.style-date-year .date::after {
  content: '';
  position: absolute;
  bottom: 0px;
  left: 17px;
  width: 4px;
  background-color: #409EFF;
  height: 20%;
  border-radius: 2px 2px 0 0;
}

.article-list-year-title.style-date-year .main-title {
  display: inline-block;
  vertical-align: middle;
  font-size: 22px;
  padding: 0 10px;
}

.article-list-year-title.style-date-year .vertical-line {
  display: inline-block;
  height: 20px;
  width: 1px;
  background-color: #333;
  vertical-align: middle;
}

.article-list-year-title.style-date-year .sub-title {
  display: inline-block;
  vertical-align: middle;
  font-size: 18px;
  padding-left: 10px;
}

.article-list-year-title.style-date-year .view-more {
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  padding-left: 10px
}

.article-list-year-title.style-date-year .view-more a {
  color: #409EFF;
  font-weight: 500;
  cursor: pointer;
}

.article-list-year-title.style-date-year .view-more a:hover {
  text-decoration: underline;
}

.article-list-year-title.style-date-month {
  padding: 15px 0;
  line-height: 30px;
}

@media only screen and (max-width: 720px) {
  .article-list-year-title.style-date-month {
    padding: 8px 0;
  }
}

.article-list-year-title.style-date-month .date {
  display: inline-block;
  margin-left: 8px;
  font-size: 24px;
  vertical-align: middle;
}

.article-list-year-title.style-date-month .date::before {
  content: '';
  position: absolute;
  top: 0px;
  left: 17px;
  width: 4px;
  background-color: #409EFF;
  height: 20%;
  border-radius: 0 0 2px 2px;
}

.article-list-year-title.style-date-month .date::after {
  content: '';
  position: absolute;
  bottom: 0px;
  left: 17px;
  width: 4px;
  background-color: #409EFF;
  height: 20%;
  border-radius: 2px 2px 0 0;
}

.article-list-year-title.style-date-month .main-title {
  display: inline-block;
  vertical-align: middle;
  font-size: 18px;
  padding: 0 10px;
}

.article-list-year-title.style-date-month .vertical-line {
  display: inline-block;
  height: 20px;
  width: 1px;
  background-color: #333;
  vertical-align: middle;
}

.article-list-year-title.style-date-month .sub-title {
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  padding-left: 10px;
}

.article-list-year-title.style-date-month .view-more {
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  padding-left: 10px;
}

.article-list-year-title.style-date-month .view-more a {
  color: #409EFF;
  font-weight: 500;
  cursor: pointer;
}

.article-list-year-title.style-date-month .view-more a:hover {
  text-decoration: underline;
}
</style>