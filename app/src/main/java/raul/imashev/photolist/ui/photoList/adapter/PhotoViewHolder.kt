package raul.imashev.photolist.ui.photoList.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import raul.imashev.photolist.R

class PhotoViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.image)
}