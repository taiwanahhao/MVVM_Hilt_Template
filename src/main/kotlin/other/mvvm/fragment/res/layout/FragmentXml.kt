package other.mvvm.fragment.res.layout

fun FragmentXml(
        viewClass: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">

    <androidx.coordinatorlayout.widget.CoordinatorLayout
        android:id="@+id/col_${viewClass.toLowerCase()}"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <include layout="@layout/layout_collapsing_appbar" />

        <androidx.core.widget.NestedScrollView
            android:id="@+id/nsv_${viewClass.toLowerCase()}"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fillViewport="true"
            app:layout_behavior="com.google.android.material.appbar.AppBarLayout$ScrollingViewBehavior">

            <androidx.constraintlayout.widget.ConstraintLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content">

            </androidx.constraintlayout.widget.ConstraintLayout>
        </androidx.core.widget.NestedScrollView>
    </androidx.coordinatorlayout.widget.CoordinatorLayout>
</androidx.constraintlayout.widget.ConstraintLayout>
"""