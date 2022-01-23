package raul.imashev.photolist.ui.photoList.adapter

import androidx.recyclerview.widget.DiffUtil

class PhotoCallBack: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}