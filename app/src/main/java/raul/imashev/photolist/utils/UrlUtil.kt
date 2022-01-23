package raul.imashev.photolist.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import raul.imashev.photolist.R

class UrlUtil {
    fun createImageUrl(path: String?, imageView: ImageView) {
        if (path?.contains("https") == true) {
            Picasso
                .get()
                .load(path)
                .error(R.drawable.error_image)
                .into(imageView)
        } else {
            Picasso
                .get()
                .load(path?.replace("http", "https"))
                .error(R.drawable.error_image)
                .into(imageView)
        }
    }
}