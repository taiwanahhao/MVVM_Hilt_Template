package other.mvvm

fun AndroidManifest(
        viewClass: String,
        packageName: String
) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android" >

<application>
<activity android:name="${packageName}.${viewClass}Activity"
android:screenOrientation="portrait"
android:theme="@style/AppTheme" />
</application>

</manifest>
"""