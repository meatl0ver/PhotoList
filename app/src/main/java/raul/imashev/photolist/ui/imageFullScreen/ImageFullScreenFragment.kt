package raul.imashev.photolist.ui.imageFullScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import raul.imashev.photolist.PATH_CONST
import raul.imashev.photolist.R
import raul.imashev.photolist.databinding.FragmentImageFullScreenBinding
import raul.imashev.photolist.utils.UrlUtil

class ImageFullScreenFragment() : Fragment() {

    private lateinit var binding: FragmentImageFullScreenBinding
    private val urlUtil = UrlUtil()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_full_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageFullScreenBinding.bind(view)
        val bundle = this.arguments
        bundle?.let {
            //Метод для получения https соединения
            urlUtil.createImageUrl(bundle.getString(PATH_CONST), (binding.ivFullScreen))
        }
    }

}