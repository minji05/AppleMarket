package com.example.applemarket

import android.os.Parcel
import android.os.Parcelable

data class MyItem(
    val productImg: Int,
    val productName: String,
    val explain: String,
    val seller: String,
    val price: Int,
    val address: String,
    var likeCount: Int = 0,
    val commentCount: Int,
    var isLike: Boolean = false
) : Parcelable {

    // Parcelable 인터페이스를 구현하기 위한 코드
    override fun describeContents(): Int {
        return 0
    }

    //writeToParcel() 메서드는 객체의 데이터를 Parcel 객체에 쓰는 메서드
    //각 속성들을 Parcel 객체에 쓰기 위해 사용되며, 데이터를 저장하는 역할
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(productImg)
        dest.writeString(productName)
        dest.writeString(explain)
        dest.writeString(seller)
        dest.writeInt(price)
        dest.writeString(address)
        dest.writeInt(likeCount)
        dest.writeInt(commentCount)
    }

    //CREATOR는 Parcelable 객체를 생성하고 배열을 생성하기 위한 컴패니언 객체
    //createFromParcel() 메서드는 Parcel 객체로부터 데이터를 읽어와 MyItem 객체를 생성
    //newArray() 메서드는 Parcelable 객체의 배열을 생성
    companion object CREATOR : Parcelable.Creator<MyItem> {
        override fun createFromParcel(parcel: Parcel): MyItem {
            return MyItem(
                parcel.readInt(),
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readInt(),
                parcel.readString() ?: "",
                parcel.readInt(),
                parcel.readInt()
            )
        }

        override fun newArray(size: Int): Array<MyItem?> {
            return arrayOfNulls(size)
        }
    }
}