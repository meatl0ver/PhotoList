package raul.imashev.photolist.ui.photoList.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import raul.imashev.photolist.PHONE_LAYOUT_SIZE_CONST
import raul.imashev.photolist.R
import raul.imashev.photolist.TABLET_LAYOUT_SIZE_CONST
import raul.imashev.photolist.utils.UrlUtil

class PhotoAdapter : ListAdapter<String, PhotoViewHolder>(PhotoCallBack()) {

    var isTablet = false
    var onItemClickListener: ((String) -> Unit)? = null
    private val urlUtil = UrlUtil()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.imageView.setOnClickListener {
            onItemClickListener?.invoke(getItem(position))
        }
        //Метод для получения https соединения
        urlUtil.createImageUrl(getItem(position), holder.imageView)

        //задание ширины и высоты элементов, исходя из устройства
        if (isTablet) {
            holder.imageView.layoutParams.width =
                Resources.getSystem().displayMetrics.widthPixels / TABLET_LAYOUT_SIZE_CONST
        } else {
            holder.imageView.layoutParams.width =
                Resources.getSystem().displayMetrics.widthPixels / PHONE_LAYOUT_SIZE_CONST
        }
        holder.imageView.layoutParams.height = holder.imageView.layoutParams.width

    }
}