package raul.imashev.photolist.ui.main

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import raul.imashev.photolist.IS_TABLET_CONST
import raul.imashev.photolist.R
import raul.imashev.photolist.ui.photoList.PhotoListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //проверка на планшет
        val isTablet =
            applicationContext.getSystemService(Context.TELEPHONY_SERVICE) == TelephonyManager.PHONE_TYPE_NONE

        val fragment = PhotoListFragment()
        val bundleIsTablet = Bundle()
        fragment.arguments = bundleIsTablet

        if (savedInstanceState == null) {
            bundleIsTablet.putBoolean(IS_TABLET_CONST, isTablet)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }
}