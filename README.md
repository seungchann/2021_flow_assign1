# Parcode  
> Flow 4분반 팀1 (승찬, 수정, 수빈)  
![Screenshot_20220104-204324_Parcode](https://user-images.githubusercontent.com/63276842/148055498-8ceb0d9e-1b10-40ae-9c80-5201cfa0941e.jpg)  
![Screenshot_20220104-205158_Parcode](https://user-images.githubusercontent.com/63276842/148055626-a49176ba-7dc5-4e87-bcee-71ff6ccd50ae.jpg)  
![Screenshot_20220104-204758_Parcode](https://user-images.githubusercontent.com/63276842/148055660-e92368cb-c29e-493f-b89d-7edc9cebe44f.jpg)  
스크린샷 (Main화면, Tab1, Tab2, Tab3)  
* 홈 파티를 위한 티켓을 생성해주는 Android 기반 어플리케이션입니다.  
* 홈 파티 멤버들의 연락처와 주소를 저장할 수 있습니다.  
* 티켓을 만들고, 바코드를 통해 멤버들과 공유가 가능합니다.  
* 만들어진 홈 파티 티켓을 저장하고 관리할 수 있습니다.  
***

### A. 개발 환경  
* OS: Android (minSdk: 21, targetSdk: 31)  
* Language: Kotlin  
* IDE: Android Studio  
* Target Device: Galaxy S7  
***

### B. 어플리케이션 소개  
#### TAB 1 - 연락처    
![Screenshot_20220104-204359_Parcode](https://user-images.githubusercontent.com/63276842/148055718-96be34ca-b403-472e-8356-cfbd8c218bc2.jpg)  

#### Major features   
* Contect tab의 우측 상단에 위치한 “ADD” 버튼을 통해 연락처를 추가할 수 있는 화면으로 이동할 수 있습니다.  
  * 아이콘 이미지를 눌러 원하는 아이콘으로 변경할 수 있습니다.  
  * 주소를 입력할 때에는 도로명 주소를 입력하기 위해, 우측에 위치한 돋보기 모양 버튼을 눌러 도로명 주소를 받아올 수 있습니다.  
  * 모든 정보를 입력한 후 “FINISH”버튼을 눌러 연락처 정보 저장을 완료합니다.  
* Tab1에서는 현재 저장된 연락처들을 확인할 수 있습니다.  
  * 인물 item을 누르면 해당 인물의 상세 정보를 확인할 수 있습니다.  
  * 상세정보에는 인물의 이름과 번호, 주소가 표시됩니다.  
* 인물 상세정보 탭의 우측 상단에 위치한 “EDIT” 버튼을 통해 해당 연락처를 수정할 수 있습니다.  
* 인물 리스트의 인물을 좌측으로 스와이프하여 삭제할 수 있습니다.  
***
#### 기술 설명  
* Recycler View를 이용하여 저장된 인물정보를 보여준다. 보여줄 인물이 많을 경우 넘어가 보이지 않는 view를 재사용한다.  
* 연락처를 저장할 때에는 사용자로부터 받은 데이터를 json file 형식으로 변환하여 local storage에 저장한다.  
* 앱 실행시 기존에 저장해두었던 jsonfile을 로드하여 인물정보를 보여준다.  
* 인물 정보를 저장할 때 도로명 주소를 받아오기 위해 다음 도로명 주소 api를 이용한다.  
***
#### Recycler View  
* 만일 스크롤 되어 보이지 않는 view가 있다면 해당 view를 재사용하여 새로운 항복을 보여준다. - 앱의 응답성을 개선, 전력 소모를 줄여 성능을 개선  
* view holder 생성 후 뷰의 data에 바인딩하여 아이템을 보여준다.  
* (view를 data에 연결하는 process를 바인딩이라 한다.)  
* 데이터를 viewholder뷰와 연결하는 adapter를 정의해야한다.  
* LayoutManager를 통해 recyclerview 항목을 정렬한다.  
* 개별 항목의 layout도 디자인 해야한다.  
* layout 디자인 후, adapter와 viewholder를 구현해야한다.  
* viewHolder == 목록에 있는 개별 항목의 레이아웃을 포함하는 view의 래퍼  
***
#### override function  
* `onCreateViewHolder()` - viewholder와 연결된 view 생성 및 초기화 (view holder가 아직 특정 data에 바인된 상태가 아니기에 content를 채우지 않는다)  
* `onBindViewHolder()` - viewHolder와 data가 연결될 때 호출되는 method (view holder의 레이아웃을 채운다)  
* `getItemCount()` - Recycler view가 data set 크기를 가져갈 때 호출되는 method  
***

#### TAB 2 - 티켓 갤러리  
![Screenshot_20220104-205158_Parcode](https://user-images.githubusercontent.com/63276842/148055822-5dae773f-ffb0-4d5b-b042-404f7ad9ad1c.jpg)  
![Screenshot_20220104-204813_Parcode](https://user-images.githubusercontent.com/63276842/148055831-bccf473d-69a0-4a35-ada4-b29c25bbbd18.jpg)  

#### Major features   
* TAB 3에서 생성된 티켓을 확인하고, 관리할 수 있습니다.  
* 좌,우로 스와이프하여 갤러리에 다른 티켓을 표시할 수 있습니다.  
* Gallery tab의 우측 상단에 위치한 "SETTING" 버튼을 통해 관리할 수 있는 화면으로 이동할 수 있습니다.  
  * DRAG & DROP 기능을 통해 티켓 순서를 수정할 수 있습니다.  
  * 티켓 리스트의 티켓을 좌측으로 스와이프하여 삭제할 수 있습니다.  
* Gallery tab의 우측 상단에 위치한 "RETURN" 버튼을 통해 다시 티켓 화면으로 돌아올 수 있습니다.  
***
#### 기술 설명  
1. `Viewpager2`와 `dotIndicator`를 활용하여, 좌/우로 스와이프가 가능한 티켓 갤러리를 제작했습니다.  
#### [ViewPager2 샘플 코드](https://github.com/android/views-widgets-samples/tree/master/ViewPager2)  
2. Front와 Back으로 나눈 후 animation를 활용하여 Card가 Flip되는 효과를 구현했습니다.  
#### back_animator.xml  
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <objectAnimator
        android:valueFrom="-180"
        android:valueTo="0"
        android:propertyName="rotationY"
        android:duration="750" />

    <objectAnimator
        android:valueFrom="0"
        android:valueTo="0"
        android:propertyName="alpha"
        android:startOffset="0"
        android:duration="450"/>

    <objectAnimator
        android:valueFrom="0"
        android:valueTo="1.0"
        android:propertyName="alpha"
        android:startOffset="450"
        android:duration="200"/>

</set>
```

***

### TAB 3 - 티켓 생성 및 불러오기  
![Screenshot_20220104-204704_Parcode](https://user-images.githubusercontent.com/63276842/148055901-f7589772-b475-4bb6-82bd-bfd62d87fa7a.jpg)  
![Screenshot_20220104-204719_Parcode](https://user-images.githubusercontent.com/63276842/148055895-000bceda-14cd-4423-a83b-2c3823f29574.jpg)  
![Screenshot_20220104-204726_Parcode](https://user-images.githubusercontent.com/63276842/148055898-5acc0bb1-c230-4e52-900b-18db90d76968.jpg)  
![Screenshot_20220104-204741_Parcode](https://user-images.githubusercontent.com/63276842/148055915-ff33f66d-a7f8-4102-98de-2edfb09bedb2.jpg)  

#### 기능 설명  
* 홈파티 이름, 일정, 참가자 등의 정보를 작성하여 티켓을 생성할 수 있습니다.  
* 이미 만들어진 티켓의 바코드를 스캔하여 데이터를 저장할 수 있습니다.  

#### 메인 기술 설명  
1. 티켓 생성  
* 사용자가 파티 정보를 하나씩 입력할 때마다 마치 티켓이 인쇄되는 듯한 효과를 구현하였습니다.  
* 사용자 입력에 대한 반응이 시각적으로 전달되도록 [ObjectAnimator](https://developer.android.com/reference/android/animation/ObjectAnimator) 클래스를 사용하였습니다.  
* 예를 들어, 사용자가 선택한 색상이 크게 강조되는 효과가 다음과 같이 구현되었습니다.  
```kotlin
for(i in 0 .. 3){
            if( i == selected ) scaleList[i] = 1F
            else scaleList[i] = 0.7F
            ObjectAnimator.ofFloat(viewList[i], "scaleX", scaleList[i]).apply {
                duration = 100
                start()
            }
            ObjectAnimator.ofFloat(viewList[i], "scaleY", scaleList[i]).apply {
                duration = 100
                start()
            }
        }
```
* 또한, 입력의 변화가 일어날 때마다 결과가 즉시 보여지도록 Custom EventListener를 사용하여 직접 애니메이션 효과를 구현하기도 했습니다.  
* 입력 페이지가 넘어갈 때 마다 티켓이 올라오는 효과는 ViewPager.OnPageChangeListener를 오버라이드하여 구현되었습니다.  

2. 티켓 불러오기  
* [ZXing](https://github.com/journeyapps/zxing-android-embedded) 라이브러리를 이용하여 각 티켓의 정보를 담고 있는 바코드를 생성하고 스캔하는 기능을 구현하였습니다.  
