package com.mustafa.newsapp.repository

import androidx.lifecycle.LiveData
import com.mustafa.newsapp.api.NewsApi
import com.mustafa.newsapp.model.Article
import com.mustafa.newsapp.model.NewsResponse
import com.mustafa.newsapp.room.ArticleDao
import com.mustafa.newsapp.room.ArticleDatabase
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val db: ArticleDatabase,
    private val service: NewsApi,
    private val dao: ArticleDao
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        return service.getBreakingNews(countryCode, pageNumber)
    }

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> {
        return service.searchForNews(searchQuery, pageNumber)
    }

    suspend fun insertArticle(article: Article) {
        dao.insertArticle(article)
    }

    fun getSavedNews(): LiveData<List<Article>> {
       return  dao.getAllArticles()
    }

    suspend fun deleteArticle(article: Article) {
        dao.deleteArticle(article)
    }
}