import Vue from 'vue'
import CommonLayout from '../components/Layout/BaseLayout/Common'
import SectionTitle from '../components/Common/SectionTitle'
import ArchiveTimeTitle from '../components/Archive/ArchiveTimeTitle'
import ArchiveCell from '../components/Archive/ArchiveCell'
import SideToc from '../components/Aside/SideToc'
import ArticlesHeader from "../components/Article/ArticleHeader";
import ArticleCell from '../components/Article/ArticleCell'
import Classify from '../components/Article/Classify'
import BrowseMore from '../components/Common/Browsemore'
import TitleMenuFilter from '../components/Common/TitleMenuFilter'
import Panel from '../components/Common/Panel'
import HotRead from '../components/Aside/HotRead'
import Profile from '../components/Aside/Profile'
import FriendLinks from '../components/Aside/FriendLinks'
import Carousel from '../components/Carousel'
import Header from '../components/Layout/Header'
import TagWall from '../components/Aside/TagWall'
import Recommend from '../components/Aside/Recommend'
import Footer from '../components/Layout/Footer'
import BaseLayout from '../components/Layout/BaseLayout'


// 全局注册组件
Vue.component("common-layout",CommonLayout)
Vue.component("section-title",SectionTitle)
Vue.component("archive-time-title",ArchiveTimeTitle)
Vue.component("archive-cell",ArchiveCell)
Vue.component("side-toc",SideToc)
Vue.component("articles-header",ArticlesHeader)
Vue.component("article-cell",ArticleCell)
Vue.component("classify",Classify)
Vue.component("browse-more",BrowseMore)
Vue.component("hot-read",HotRead)
Vue.component("profile",Profile)
Vue.component("friend-links",FriendLinks)
Vue.component("bg-carousel",Carousel)
Vue.component("bg-header",Header)
Vue.component("tag-wall",TagWall)
Vue.component("recommend",Recommend)
Vue.component("title-menu-filter",TitleMenuFilter)
Vue.component("panel",Panel)
Vue.component("bg-footer",Footer)
Vue.component("base-layout",BaseLayout)

