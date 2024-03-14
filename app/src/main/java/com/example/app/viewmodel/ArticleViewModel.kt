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
}