# AndroidRecyclerViewWithLoadingEmptyAndRetry

![alt tag](/image.gif)

## How to apply it to project

1. Copy [RecyclerViewEmptyRetryGroup.java](/app/src/main/java/com/toong/recyclerviewwithemptyandretry/widget/RecyclerViewEmptyRetryGroup.java) to your project
2. Replace your current **RecyclerView** by the group view below
```
<com.toong.recyclerviewwithemptyandretry.widget.RecyclerViewEmptyRetryGroup
    android:id="@+id/recyclerViewEmptyRetryGroup"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <LinearLayout
        android:id="@+id/layout_empty"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:orientation="vertical">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:text="No data"/>
        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:src="@mipmap/ic_launcher"/>
    </LinearLayout>

    <LinearLayout
        android:id="@+id/layout_retry"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:orientation="vertical">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:text="Load failed"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:text="Try again"/>
    </LinearLayout>

    <ProgressBar
        android:id="@+id/progress_bar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"/>

</com.toong.recyclerviewwithemptyandretry.widget.RecyclerViewEmptyRetryGroup>
```
refer: [activity_main.xml](/app/src/main/res/layout/activity_main.xml)

3. In your activity
```
private RecyclerViewEmptyRetryGroup mRecyclerViewEmptyRetryGroup;

protected void onCreate(Bundle savedInstanceState) {
   ...
   mRecyclerViewEmptyRetryGroup =(RecyclerViewEmptyRetryGroup) findViewById(R.id.recyclerViewEmptyRetryGroup);
   // retrieve RecyclerView like this
   RecyclerView recyclerView = mRecyclerViewEmptyRetryGroup.getRecyclerView();

   // do another step with your RecyclerView normally
}

/*
    SOME METHOD THAT USE NEED TO USE
*/
private void startLoadData(){
     mRecyclerViewEmptyRetryGroup.loading();
     ...
}

private void onLoadDataSuccess(){
     mRecyclerViewEmptyRetryGroup.success();
     ...
}

private void onLoadDataFailed(){
     mRecyclerViewEmptyRetryGroup.retry();
     ...
}

private void onLoadDataEmpty(){
     mRecyclerViewEmptyRetryGroup.empty();
     ...
}

```


4. Done

refer: [MainActivity.java](/app/src/main/java/com/toong/recyclerviewwithemptyandretry/MainActivity.java)