<template>
  <div>
    <common-layout>
      <template v-slot:content>
        <section-title main-title="存档时光" sub-title="Timeline"/>
        <div class="timeline-content">
          <div v-for="year in timelineList" :key="year.year">
            <div v-if="year.months.length > 0">
              <archive-time-title :date="year.year" :count="year.count"></archive-time-title>
              <div v-for="month in year.months" :key="month.month">
                <div v-if="month.timelines.length > 0">
                  <archive-time-title :date="month.month + '月'" :count="month.count"
                                      :dateType="'month'"></archive-time-title>
                  <archive-cell v-for="post in month.timelines" :post="post"
                                :key="post.title"></archive-cell>
                </div>
              </div>
            </div>
          </div>
        </div>
      </template>
    </common-layout>
  </div>
</template>

<script>
import {TimelineList } from '../../api/all';
export default {
  data() {
    return {
      timelineList: []
    };
  },

  components: {},
  created() {
    this.listTimeline()
  },
  methods: {
    listTimeline() {
      TimelineList().then(res =>{
        this.timelineList = res.data
      });
    }
  }
};
</script>

<style>

</style>
