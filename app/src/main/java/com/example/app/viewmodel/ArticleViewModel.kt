package com.example.app.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app.model.entity.ArticleEntity

class ArticleViewModel : ViewModel() {
    //文章列表数据
    var list = listOf(
        ArticleEntity(title = "title1", source = "source1", timestamp = "time1"),
        ArticleEntity(title = "title1", source = "source1", timestamp = "time1"),
        ArticleEntity(title = "title1", source = "source1", timestamp = "time1"),
        ArticleEntity(title = "title1", source = "source1", timestamp = "time1"),
        ArticleEntity(title = "title1", source = "source1", timestamp = "time1"),
        ArticleEntity(title = "title1", source = "source1", timestamp = "time1"),
        ArticleEntity(title = "title1", source = "source1", timestamp = "time1"),
        ArticleEntity(title = "title1", source = "source1", timestamp = "time1"),
        ArticleEntity(title = "title1", source = "source1", timestamp = "time1"),
        ArticleEntity(title = "title1", source = "source1", timestamp = "time1"),
    )
        private set


    val content = """
        <html>
        	<head>
        		<meta charset="utf-8">
        		<title></title>
        	</head>
        	<body align = "center">
        	<h1> 古诗词鉴赏</h1>
        		<hr />
        		<h2 >静夜思</h2>
        		<a href="https://baike.baidu.com/item/%E6%9D%8E%E7%99%BD/1043?fr=aladdin">李白</a>
        		<p>床前明夜光,</p>
        		<p>疑是地上霜.</p>
        		<p>举头望明月,</p>
        		<p>低头思故乡.</p>
        		《静夜思》是唐代诗人李白所作的一首五言古诗 。<br />
        		此诗描写了秋日夜晚，诗人于屋内抬头望月的所感。<br />
        		诗中运用比喻、衬托等手法，表达客居思乡之情，<br />
        		语言清新朴素而韵味含蓄无穷，历来广为传诵。<br />
        		<img src="img/libai.jpg" alt="网络不佳,请重试!" title="李白" />
        <hr />
    """.trimIndent()
}