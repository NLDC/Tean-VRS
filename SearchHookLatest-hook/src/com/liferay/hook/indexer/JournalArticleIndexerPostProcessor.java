package com.liferay.hook.indexer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletURL;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portlet.journal.model.JournalArticle;


public class JournalArticleIndexerPostProcessor extends BaseIndexerPostProcessor {

    public void postProcessContextQuery(BooleanQuery booleanQuery, SearchContext searchcontext)
            throws Exception {
        if(_log.isDebugEnabled())
            _log.debug(" postProcessContextQuery()");
    }

    public void postProcessDocument(Document document, Object object)
            throws Exception {
        JournalArticle journalArticle = (JournalArticle) object;
        System.out.println("in the postProcessDocument method Ren :-)");
        String indexerUserTitle = "";
        System.out.println("in the postProcessDocument method Ren :-");
        Date createDate;
        Date modifiedDate;
        Date publishDate;
        try {
            createDate = journalArticle.getCreateDate();
            modifiedDate = journalArticle.getModifiedDate();
            publishDate = journalArticle.getExpirationDate();
            if(createDate != null){
            	 document.addDate(Field.CREATE_DATE, createDate);
            }
            if(modifiedDate != null){
           	 document.addDate(Field.MODIFIED_DATE, modifiedDate);
           }
            if(publishDate != null){
           	 document.addDate(Field.PUBLISH_DATE, publishDate);
           }

        } catch (Exception e) {}
        if(indexerUserTitle.length() > 0)
            document.addText(Field.TITLE, indexerUserTitle);
    }

    public void postProcessFullQuery(BooleanQuery fullQuery, SearchContext searchcontext)
            throws Exception {
        if(_log.isDebugEnabled())
            _log.debug(" postProcessFullQuery()");
        System.out.println("in the postProcessFullQuery method Ren :-)");
    }

    public void postProcessSearchQuery(BooleanQuery searchquery, SearchContext searchcontext)
            throws Exception {
        if(_log.isDebugEnabled())
            _log.debug(" postProcessSearchQuery()");
        System.out.println("in the postProcessSearchQuery method Ren :-)");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        
        //return number of milliseconds since January 1, 1970, 00:00:00 GMT
        System.out.println(timestamp.getTime());
        //20170416000000 TO 20170416235959]
        searchquery.addRangeTerm(Field.CREATE_DATE, 20170416000000L, 20170416235959L);
        Map<String, Serializable> map = searchcontext.getAttributes();
        Set set = map.keySet();
        Iterator valuesIterator = map.values().iterator();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
        	String key = (String)iterator.next();
        	System.out.println("key string : "+key);
        }
        while(valuesIterator.hasNext()){
        	String value= (String)valuesIterator.next();
        	System.out.println("value string : "+value);
        }
    }

    public void postProcessSummary(Summary summary, Document document, Locale locale,
            String snippet, PortletURL portletURL) {
        if(_log.isDebugEnabled())
            _log.debug("postProcessSummary()");
    }

    private static Log _log = LogFactoryUtil.getLog(JournalArticleIndexerPostProcessor.class);
}