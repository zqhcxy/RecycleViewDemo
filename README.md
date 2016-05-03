RecycleViewDemo
========

#简介：
    需要导入的包：
        compile 'com.android.support:recyclerview-v7:23.3.0'
        
    主要内容是RcyclerView 的使用：
##a、分为简单的显示使用：
          item的添加、删除、修改、点击事件.
##b、多类型item。
##c、RecyclerView的CursorAdapter使用。

#代码实现：
```java
        refresh_ly = (SwipeRefreshLayout) findViewById(R.id.refresh_ly);
        //横向和纵向
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);//如果每个item的类型是一样的，可以设置为true加快运行效率
```
