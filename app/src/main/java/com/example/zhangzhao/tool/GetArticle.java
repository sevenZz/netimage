package com.example.zhangzhao.tool;

import com.example.zhangzhao.entity.Article;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao on 2015/5/13.
 */
public class GetArticle {
    public GetArticle(final String id, final SuccessCallback successCallback, final FailCallback failCallback){
        new NetConnection(Config.URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);

                    switch (obj.getInt(Config.STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback != null){
                                Article article = new Article();
                                article.setTitle(obj.getString(Config.ARTICLE_TITLE));
                                article.setArticleAuthor(obj.getString(Config.ARTICLE_AUTHOR));
                                article.setArticle(obj.getString(Config.ARTICLE_CONTENT));

                                successCallback.onSuccess(article);
                            }

                            break;
                        default:
                            if(failCallback != null) {
                                failCallback.onFail(Config.RESULT_STATUS_FAIL);
                            }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(int errorCode) {

            }
        }, Config.ARTICLE_ID, id);
    }

    public static interface SuccessCallback{
        void onSuccess(Article article);
    }
    public static interface FailCallback{
        void onFail(int errorCode);
    }
}
