package com.example.walkly.domain.model.mymap

import com.google.android.gms.maps.model.Marker
import java.util.*

/**
 * アクティビティの目的地となるマーカー一覧を管理する
 */
class MarkerList {
    companion object {
        const val MAX_INDEX = 10
    }
    private var list: Queue<Marker> = ArrayDeque(MAX_INDEX)

    /**
     * マーカーをリストに追加し、もし上限以上なら最初の1つを削除する
     *
     * @param marker
     */
    fun add(marker: Marker) {
        list.add(marker)
        if (list.size > MAX_INDEX) {
            list.poll()?.remove()
        }
    }

    /**
     * 同じ場所のマーカーを設置しようとしている?
     * Marker.titleで判断
     *
     * @param name
     * @return Boolean
     */
     fun checkDuplicate(name: String): Boolean {
        var flag = false
        list.forEach {
            if (it.title == name) flag = true
        }
        return flag
    }

}