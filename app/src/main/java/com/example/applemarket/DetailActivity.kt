package com.example.applemarket

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // MainActivity에서 넘어온 데이터 가져오기
        val selectedItem = intent.getParcelableExtra<MyItem>("selectedItem")

        // 데이터를 가져와서 해당하는 뷰에 설정
        binding.imageView.setImageResource(selectedItem?.productImg ?: R.drawable.sample1)
        binding.nickname.text = selectedItem?.seller
        binding.location.text = selectedItem?.address

        // 가격을 천단위로 포맷팅하여 설정
        val priceFormat = DecimalFormat("#,###")
        val formattedPrice = priceFormat.format(selectedItem?.price ?: 0)
        binding.price.text = formattedPrice + "원"

        binding.productName.text = selectedItem?.productName
        binding.productContent.text = selectedItem?.explain

        binding.likeBtn.setOnClickListener {
            LikeStatus(selectedItem)
        }


//        // '<' 버튼 클릭 시 종료
//        binding.backBtn.setOnClickListener {
//            finish()
//        }
    }
    private fun LikeStatus(item: MyItem?) {
        item?.let {
            val returnIntent = Intent()
            val position = intent.getIntExtra("position", -1)

            it.isLike = !it.isLike // 좋아요 상태를 토글

            if (it.isLike) {
                binding.likeBtn.setImageResource(R.drawable.heart)
                showSnackbar("관심 목록에 추가되었습니다.")
                it.likeCount += 1
                returnIntent.putExtra("liked", true)
            } else {
                binding.likeBtn.setImageResource(R.drawable.unheart)
                it.likeCount -= 1
                returnIntent.putExtra("liked", false)
            }

            returnIntent.putExtra("updatedItem", it)
            returnIntent.putExtra("position", position)
            setResult(Activity.RESULT_OK, returnIntent) // 결과 설정
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }



}