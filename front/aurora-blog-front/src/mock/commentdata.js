//模拟评论数据
const comment = {
    status: "成功",
    code: 200,
    data: [
        {
            id: "1",
            type: false,
            ownerId: "9527",
            parentId: "0",
            name: "网友1",
            avatar: "http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg",
            likeNum: 10,
            dislikeNum: 20,
            content: "非常靠谱的程序员",
            replyId: null,
            replyName: null,
            createTime: "2021-09-14 19:14",
            updateTime: "2021-09-14 19:14",
            reply: [
                {
                    id: "2",
                    type: false,
                    ownerId: "9527",
                    parentId: "1",
                    name: "网友2",
                    avatar: "https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg",
                    likeNum: 3,
                    dislikeNum: 4,
                    content: "赞同，很靠谱，水平很高",
                    replyId: "1",
                    replyName: "网友1",
                    createTime: "2021-09-14 19:14",
                    updateTime: "2021-09-14 19:14"
                },
                {
                    id: "3",
                    type: false,
                    ownerId: "9527",
                    parentId: "1",
                    name: "网友3",
                    avatar: "http://imgsrc.baidu.com/imgad/pic/item/c2fdfc039245d688fcba1b80aec27d1ed21b245d.jpg",
                    likeNum: 6,
                    dislikeNum: 3,
                    content: "大神一个",
                    replyId: "2",
                    replyName: "网友2",
                    createTime: "2021-09-14 19:14",
                    updateTime: "2021-09-14 19:14"
                }
            ]
        },
        {
            id: "4",
            type: false,
            ownerId: "9527",
            parentId: "0",
            name: "网友4",
            avatar: "http://ww1.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2q2p8pj30v90uzmzz.jpg",
            likeNum: 8,
            dislikeNum: 2,
            content: "从没见过这么优秀的人",
            replyId: null,
            replyName: null,
            createTime: "2021-09-14 19:14",
            updateTime: "2021-09-14 19:14",
            reply: [
                {
                    id: "5",
                    type: false,
                    ownerId: "9527",
                    parentId: "4",
                    name: "网友2",
                    avatar: "https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg",
                    likeNum: 6,
                    dislikeNum: 3,
                    content: "赞同，很靠谱，水平很高",
                    replyId: "4",
                    replyName: "网友4",
                    createTime: "2021-09-14 19:14",
                    updateTime: "2021-09-14 19:14"
                }
            ]
        }
    ]
};

export {comment}
