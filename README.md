# RoundImageView
##Usage
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin">

    <com.vincent.roundimageview.view.RoundImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:src="@mipmap/image"
        app:type="circle" />

    <com.vincent.roundimageview.view.RoundImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        app:radius="20dp"
        app:src="@mipmap/image"
        app:type="round" />

    <com.vincent.roundimageview.view.RoundImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        app:radius="35dp"
        app:src="@mipmap/image"
        app:type="round" />

</LinearLayout>
##Example
![Running Effect](https://github.com/lichao315/RoundImageView/blob/master/roundimageview/example.png)
