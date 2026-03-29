package com.wanted.android.wanted.design.navigations.topbar.dialogtopbar


/**
 * object WantedDialogTopAppBarContract
 *
 * DialogTopAppBar 컴포넌트에서 사용하는 설정 값을 정의하는 객체입니다.
 */
object WantedDialogTopAppBarContract {


    /**
     * enum class Variant
     *
     * TopDialogAppBar의 형태를 정의하는 enum 클래스입니다.
     * - Normal: Title이 Center에 위치합니다.
     * - Emphasized: Title이 왼쪽에 위치합니다.
     * - Display: 디스플레이 형태입니다.
     * - Floating: 플로팅 형태입니다.
     */
    enum class Variant {
        Normal,
        Emphasized,
        Display,
        Floating
    }
}