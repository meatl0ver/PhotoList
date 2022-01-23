package raul.imashev.photolist.ui.photoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import raul.imashev.photolist.IS_TABLET_CONST
import raul.imashev.photolist.PATH_CONST
import raul.imashev.photolist.R
import raul.imashev.photolist.TABLET_LAYOUT_SIZE_CONST
import raul.imashev.photolist.databinding.FragmentPhotoListBinding
import raul.imashev.photolist.ui.imageFullScreen.ImageFullScreenFragment
import raul.imashev.photolist.ui.photoList.adapter.PhotoAdapter


class PhotoListFragment : Fragment() {
    private lateinit var viewModel: PhotoListViewModel
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var binding: FragmentPhotoListBinding

    private var isTablet = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        bundle?.let {
            isTablet = bundle.getBoolean(IS_TABLET_CONST)
        }
        binding = FragmentPhotoListBinding.bind(view)
        viewModel = ViewModelProvider(this)[PhotoListViewModel::class.java]
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            setupRecyclerView()
        }
        setupRecyclerView()
        setupClickListener()
    }

    private fun setupRecyclerView() {
        viewModel.loadData()
        if (isTablet) {
            binding.photoRv.layoutManager = GridLayoutManager(context, TABLET_LAYOUT_SIZE_CONST)
        }
        with(binding.photoRv) {
            photoAdapter = PhotoAdapter()
            photoAdapter.isTablet = isTablet
            adapter = photoAdapter

        }
        viewModel.photosList.observe(viewLifecycleOwner, {
            photoAdapter.submitList(viewModel.photosList.value)
        })
    }

    private fun setupClickListener() {
        val fragment = ImageFullScreenFragment()
        val bundleImagePath = Bundle()
        fragment.arguments = bundleImagePath
        photoAdapter.onItemClickListener = {
            bundleImagePath.putString(PATH_CONST, it)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment)
                ?.addToBackStack(null)?.commit()
        }
    }
}