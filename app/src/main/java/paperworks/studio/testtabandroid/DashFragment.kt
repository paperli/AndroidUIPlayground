package paperworks.studio.testtabandroid


import android.app.Activity.RESULT_OK
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dash.*
import java.io.File
import java.net.URI


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DashFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgv.setOnClickListener {
            val photolibraryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            photolibraryIntent.addCategory(Intent.CATEGORY_OPENABLE)

            photolibraryIntent.setType("image/*")
            startActivityForResult(photolibraryIntent, 3645)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 3645 && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri = data.data

            /*var bitmap = BitmapFactory.decodeFile(selectedImageUri.encodedPath)
            val exif = ExifInterface(selectedImageUri.path)
            var orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

            when(orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> bitmap = rotate(bitmap, 90F)
                ExifInterface.ORIENTATION_ROTATE_180 -> bitmap = rotate(bitmap, 180F)
                ExifInterface.ORIENTATION_ROTATE_270 -> bitmap = rotate(bitmap, 270F)
            }
            imgv.setImageBitmap(bitmap)*/
            imgv.setImageURI(selectedImageUri)
        }
    }

    fun rotate(bitmap: Bitmap, degree: Float): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(degree)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    fun rotate(bitmap: Bitmap, horizonal: Boolean, vertical: Boolean): Bitmap? {
        val matrix = Matrix()
        matrix.preScale(if (horizonal) -1F else 1F, if (vertical) -1F else 1F)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                DashFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
