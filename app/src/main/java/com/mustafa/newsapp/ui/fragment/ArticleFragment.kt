package com.mustafa.newsapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mustafa.newsapp.R
import com.mustafa.newsapp.adapters.NewsAdapter
import com.mustafa.newsapp.model.Article
import com.mustafa.newsapp.ui.MainActivity
import com.mustafa.newsapp.ui.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.fragment_saved_news.*

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var viewModel: NewsViewModel
    private lateinit var article: Article

    //val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        receiveArticleArgsAndOpenWebView()

        setupFloatingActionButton()
    }

    private fun receiveArticleArgsAndOpenWebView() {
        // val article = args.article
        article = ArticleFragmentArgs.fromBundle(requireArguments()).article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

    private fun setupFloatingActionButton() {
        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(requireView(), "Article saved successfully", Snackbar.LENGTH_SHORT)
                .apply {
                    setAction("Undo") {
                        viewModel.deleteArticle(article)
                    }
                    show()
                }
        }
    }
}