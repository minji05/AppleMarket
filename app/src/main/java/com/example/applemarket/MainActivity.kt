package com.example.applemarket

import android.app.Activity
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var dataList: MutableList<MyItem>
    private lateinit var binding: ActivityMainBinding
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var isVisible = false
    private lateinit var adapter: MyAdapter // Adapter 변수 선언 추가
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 데이터 원본 준비
        dataList = mutableListOf<MyItem>()
        dataList.add(
            MyItem(
                R.drawable.sample1,
                "산지 한달된 선풍기 팝니다",
                "이사가서 필요가 없어졌어요 급하게 내놓습니다",
                "대현동",
                1000,
                "서울 서대문구 창천동",
                13,
                25
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample2,
                "김치냉장고",
                "이사로인해 내놔요",
                "안마담",
                20000,
                "인천 계양구 귤현동",
                8,
                28
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample3,
                "샤넬 카드지갑",
                "고퀄지갑이구요\\n사용감이 있어서 싸게 내어둡니다",
                "코코유",
                10000,
                "수성구 범어동",
                23,
                5
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample4,
                "금고",
                "금고\\n떼서 가져가야함\\n대우월드마크센텀\\n미국이주관계로 싸게 팝니다",
                "Nicole",
                10000,
                "해운대구 우제2동",
                14,
                17
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample5,
                "갤럭시Z플립3 팝니다",
                "갤럭시 Z플립3 그린 팝니다\\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",
                "절명",
                150000,
                "연제구 연산제8동",
                22,
                9
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample6,
                "프라다 복조리백",
                "까임 오염없고 상태 깨끗합니다\\n정품여부모름",
                "미니멀하게",
                50000,
                "수원시 영통구 원천동",
                25,
                16
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample7,
                "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
                "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\\n2. 장소: 북구 블루마시티, 32-33층\\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\\n6. 고기 굽기 가능\\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\\n8. 층간소음 주의 부탁드립니다.\\n9. 방3개, 화장실3개, 비데 3개\\n10. 저희 집안이 쓰는 별장입니다.",
                "굿리치",
                150000,
                "남구 옥동",
                142,
                54
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample8,
                "샤넬 탑핸들 가방",
                "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\\n + \"\\n\" + \"색상 : 블랙\\n\" + \"사이즈 : 25.5cm * 17.5cm * 8cm\\n\" + \"구성 : 본품더스트\\n\" + \"\\n\" + \"급하게 돈이 필요해서 팝니다 ㅠ ㅠ",
                "난쉽",
                180000,
                "동래구 온천제2동",
                31,
                7
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample9,
                "4행정 엔진분무기 판매합니다.",
                "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\\n",
                "알뜰한",
                30000,
                "원주시 명륜2동",
                7,
                28
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample10,
                "셀린느 버킷 가방",
                "22년 신세계 대전 구매입니당\\n + \"셀린느 버킷백\\n\" + \"구매해서 몇번사용했어요\\n\" + \"까짐 스크래치 없습니다.\\n\" + \"타지역에서 보내는거라 택배로 진행합니당!\"",
                "똑태현",
                190000,
                "중구 동화동",
                40,
                6
            )
        )

        binding.productList.adapter = MyAdapter(dataList)

        adapter = MyAdapter(dataList)
        binding.productList.adapter = adapter
        binding.productList.layoutManager = LinearLayoutManager(this)

        // 아이템 클릭 리스너 설정
        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val selectedItem = dataList[position]

                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("selectedItem", selectedItem) // 객체를 intent에 추가
                intent.putExtra("position", position)
                startActivityForResult(intent, REQUEST_DETAIL) // DetailActivity 시작
            }
        }

        // 아이템 롱클릭  리스너 설정
        adapter.itemLongClick = object : MyAdapter.ItemLongClick  {
            override fun onLongClick(view: View, position: Int) {
                val selectedItem = dataList[position]

                DeleteDialog(selectedItem, position)
            }
        }
        binding.notification.setOnClickListener {
            notification()
        }

        // 플로팅 버튼과 스크롤 이벤트 설정
        FloatingActionButton()
    }

    // 플로팅 버튼과 스크롤 이벤트 설정 함수
    private fun FloatingActionButton() {
        // 스크롤 이벤트 리스너 설정
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val isScrollingUp = dy > 0

                if (isScrollingUp) {
                    // 스크롤을 위로 올릴 때, 플로팅 버튼 나타내기
                    showFloatingAction()
                } else {
                    // 스크롤을 아래로 내릴 때, 플로팅 버튼 숨기기
                    hideFloatingAction()
                }
            }
        }

        binding.productList.addOnScrollListener(scrollListener)

        // 플로팅 버튼 클릭 이벤트 설정
        binding.floatingBtn.setOnClickListener {
            // 스크롤을 최상단으로 이동하고 플로팅 버튼 숨기기
            binding.productList.scrollToPosition(0)
            hideFloatingAction()
        }
    }


    // 플로팅 버튼 나타내기 함수
    private fun showFloatingAction() {
        if (!isVisible) {
            isVisible = true
            // 플로팅 버튼을 나타내고 아이콘 변경 (위로 화살표 아이콘)
            binding.floatingBtn.show()
            binding.floatingBtn.setImageResource(R.drawable.uploading)
        }
    }

    // 플로팅 버튼 숨기기 함수
    private fun hideFloatingAction() {
        if (isVisible) {
            isVisible = false
            // 플로팅 버튼을 숨기고 아이콘 변경 (위로 화살표 아이콘)
            binding.floatingBtn.hide()
            binding.floatingBtn.setImageResource(R.drawable.uploading)
        }
    }


    fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 26 버전 이상
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                // 채널에 다양한 정보 설정
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this, channelId)

        } else {
            // 26 버전 이하
            builder = NotificationCompat.Builder(this)
        }

        val intent = Intent(this, DetailActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        // 알림의 기본 정보
        builder.run {
            setSmallIcon(R.drawable.circumference)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다.")
        }
        manager.notify(11, builder.build())
    }

    override fun onBackPressed() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("종료")
        builder.setMessage("정말 종료하시겠습니까?")
        builder.setIcon(R.drawable.bubble)
        builder.setPositiveButton("확인") { dialog, which ->
            finish() // 앱 종료
        }
        builder.setNegativeButton("취소") { dialog, which ->
            dialog.dismiss() // 다이얼로그 닫기
        }

        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DETAIL && resultCode == Activity.RESULT_OK && data != null) {
            val position = data.getIntExtra("position", -1)
            val updatedItem = data.getParcelableExtra<MyItem>("updatedItem")
            val liked = data.getBooleanExtra("liked", false)

            if (position != -1 && updatedItem != null) {
                // 아이템 업데이트 및 리스트 갱신
                dataList[position] = updatedItem
                adapter.notifyItemChanged(position)

                // 메인 화면의 아이템 상태 갱신
                if (liked) {
                    dataList[position].isLike = true
                    dataList[position].likeCount = updatedItem.likeCount
                } else {
                    dataList[position].isLike = false
                    dataList[position].likeCount = updatedItem.likeCount
                }

                // 메인 화면의 아이템 상태를 반영하여 화면을 갱신
                adapter.notifyDataSetChanged()
            }
        }
    }
    private fun DeleteDialog(items: MyItem, position: Int) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("상품 삭제")
        builder.setMessage("상품을 정말 삭제하시겠습니까?")
        builder.setIcon(R.drawable.bubble)
        builder.setPositiveButton("확인") { dialog, which ->
            deleteItem(position)
        }
        builder.setNegativeButton("취소") { dialog, which ->
            dialog.dismiss() // 다이얼로그 닫기
        }

        builder.show()
    }

    // 아이템을 삭제하고 리스트를 업데이트하는 함수
    private fun deleteItem(position: Int) {
        dataList.removeAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, dataList.size)
    }

    companion object {
        private const val REQUEST_DETAIL = 101
    }

    override fun onResume() {
        super.onResume()

        // 아이템 상태를 업데이트하여 화면을 갱신
        adapter.notifyDataSetChanged()
    }
}