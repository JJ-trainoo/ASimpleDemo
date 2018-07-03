package com.trainoo.crawler.novel;

import org.junit.Test;

import java.util.List;

/**
 * Created by zhoutao on 2018/7/2 14:36
 */

public class ChapterDBTest {

    @Test
    public void testSelect(){
        Chapter chapter = ChapterDB.select(1);
        System.out.println(chapter.getTitle());
    }

    @Test
    public void testSelectAll(){
        List<Chapter> list = ChapterDB.selectAll();
        if(list != null && list.size() > 0){
            for (int i = 0; i < list.size(); i ++){
                Chapter chapter = list.get(i);
                System.out.println(chapter.getTitle());
            }
        }
    }

}