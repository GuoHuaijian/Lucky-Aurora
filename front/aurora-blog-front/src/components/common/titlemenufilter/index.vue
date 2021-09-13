<template>
    <div class="title-menu-timeline">
        <ul class="list clearfix">
            <li v-for="menuFilter in filterList" :key="menuFilter.name">
                <a :class="menuFilter.active ? 'active' : ''"
                   @click="filterByMenu(menuFilter.type)"
                >{{ menuFilter.name }}</a
                >
            </li>
        </ul>
        <div class="refresh">
            <a @click="refresh" title="刷新">
                <i class="el-icon-refresh-right"></i>
            </a>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            menuFilterList: Array
        },
        computed: {
            filterList() {
                return this.menuFilterList
            }
        },
        methods: {
            refresh() {
                this.$router.go(0)
            },
            filterByMenu(type) {
                this.replaceActive(type)
                let param = {}
                param[type] = true
                this.$emit('filterByMenu', param)
            },
            replaceActive(type) {
                this.menuFilterList.map((menuFilter) => {
                    if (menuFilter.type === type) {
                        menuFilter.active = true
                    } else {
                        menuFilter.active = false
                    }
                })
            }
        }
    }
</script>

<style>
    .title-menu-timeline {
        height: 50px;
        display: flex;
    }

    .clearfix {
        height: 50px;
        margin: 0px;
    }

    .title-menu-timeline ul li {
        float: left;
        margin: 0 6px;
        font-size: initial;
    }

    .title-menu-timeline ul li a:hover {
        cursor: pointer;
        padding: 6px 0;
        color: #409eff;
        border-bottom: 2px solid #409eff;
    }

    .active {
        padding: 6px 0;
        color: #409eff;
        border-bottom: 2px solid #409eff;
    }

    .refresh {
        margin-left: 15px;
        line-height: inherit;
        font-size: large;
    }

    .refresh a:hover {
        font-size: 20px;
        color: #696969;
        cursor: pointer;
        color: #409eff;
    }
</style>
