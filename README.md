# Klijent-server aplikacija - domaci2
Da bi funkcionisala komunikacija izmedju servera i aplikacije premestite ibd fajlove u C:\ProgramData\MySQL\MySQL Server 8.0\Data\knjizara2.Server.java se moze nalaziti bilo gde.Samo promenite sifru i username.

# Offline aplikacija - domaci1
Da bi smo pokrenuli aplikaciju na nasem uredjaju moramo imati minimum Android 8.0(ili vise).
```
minSdkVersion 26
targetSdkVersion 29
```
dependencies
```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.12'

    //recycler view
    implementation 'com.android.support:recyclerview-v7:29.0.0'

    //card view
    implementation 'com.android.support:cardview-v7:29.0.0'

    //picasso
    implementation 'com.squareup.picasso:picasso:2.71828'

    //gson
    implementation 'com.google.code.gson:gson:2.8.5'

    //jitpack
    implementation 'com.github.mohammadatif:Animatoo:master'

    //butterknife
    implementation 'com.jakewharton:butterknife:10.2.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.2.1'

    //tablayout itd
    implementation 'com.google.android.material:material:1.1.0'
    
}
```
---

```MainActivity```(R.layout.main_layout) je pocetna tacka nase aplikacije.
Ona sadrzi TabLayout koji ima tri taba. PagerAdapter(FragmentPagerAdapter) sadrzi instance tih tabova,vraca broj tabova,kao i neke nase metode poput updateTab2,updateTab3, kao i 'listenere' za te tabove.
U metodi ```initData()``` pravimo instance bitnih  klasa:viewPager koristimo za postavljanje trenutnog ekrana,kada ga korisnik odabere. Ili swipe na isti. Svaki put kada swipe na tab2 ili tab3, pozivamo metodu ```listenerMojeKnjige.compare()``` koji samo poredi velicinu niza iz SharedPreferences sa nizom iz RecyclerView adaptera tog taba. Ukoliko primeti razliku azurira RecyclerView adapter.
Dugim pritiskom na tab2 imamo kratak uvid u to koje je knjige korisnik kupio kao i koliko je novca ukupno potrosio.
```onRestart()``` metoda MainActivity se aktivira svaki put kada se korisnik vrati iz neke druge aktivnosti,kao PlacanjeActivity i ona na za notifikaciju u vezi kupljenih knjiga. Samo za ta dva slucaja obavlja neku radnju za ostale se normalno restartuje i naravno ne poziva se onCreate().
---
```Tab1```(fragment_tab1) je prvo sta korisnik *vidi* kada otvori aplikaciju. Posto ce na prvom ekranu da se nalaze knjige,koristimo klasu KnjizaraInfo koja poziva metodu za citanje objekata iz out fajla koji sadrzi dve liste sa ```Knjiga``` objektima(knjige koje se placaju i besplatne knjige). Klase za SharedPreferences su tu samo da postave podatke na pocetku.
```java
if(!isInitAppDataDone()) {
    initAppData();
    Log.i("MainActivity ","Setup appdata upravo gotov.");
}
else {
    Log.i("MainActivity ","Setup appdata uradjen ranije. ");
}
```
```java
public void initAppData() {

    Korisnik korisnik = new Korisnik();
    ArrayList<Knjiga> korpa = new ArrayList<Knjiga>();
    ArrayList<Knjiga> mojeKnjige = new ArrayList<Knjiga>();

    String jsonKorisnik = gson.toJson(korisnik);
    String jsonKorpa = gson.toJson(korpa);
    String jsonMojeKnjige = gson.toJson(mojeKnjige);

    prefsEditor.putString("korisnik",jsonKorisnik);
    prefsEditor.putString("korpa",jsonKorpa);
    prefsEditor.putString("mojeKnjige",jsonMojeKnjige);
    prefsEditor.putBoolean("korisnikBack",false);
    prefsEditor.putInt("currentTab",0);

    prefsEditor.apply();

}
```
Da bi korisnik mogao da kupuje knjige neophodno je da ima nalog zato na pocetku pozivamo metodu requestNotification koja se kreira samo ako korisnik nije ulogovan.

```initRecyclerView```
```java
public void initRecyclerView (String kat) { ....
```
Za svaku kategoriju koristimo RecyclerView,prve dve koriste horizontalni LinearLayout dok poslednja koristi GridLayoutManager sa dve stavke u jednom redu.

---
```KnjigaDetail i KategorijaActivity```
U RV onClick za CardView,uzimamo objekat iz niza u stavljamo u Intent.Klikom na knjigu iz bilo koje kategorije(kategorije na top levelu activity,ne misli se na kategoriju knjige) pokrece se aktivnost KnjigaDetail koja iz Intenta uzima objekat knjige i tako postavlja TextViews i Buttons. 
Klikom na kategoriju iz KnjigeDetail pocinjemo novu aktivnost KategorijaActivity koja ce sadrzati niz sa odabranom kategorijom. Za tu akciju citamo iz KnjizaraInfo oba objekta i spajamo ih u jedan niz. Zatim trazimo knjige koje spadaju pod odabranu kategoriju. Novi niz stavljamo u adapter za RV.
Na istoj aktivnosti mozemo dodati knjige u korpu(sharedpreferences koristimo za ovo),komentarisati i oceniti knjige.

---

```Tab2``` ima slican adapter kao adapter za hronolosku listu. Ovaj tab se azurira svaki put kada se kupi nova knjiga. 

---
```Tab3``` se azuirira svaki put kada korisnik doda novu knjigu u korpu. Da bi se obrisala knjiga iz korpe ide se na "obrisi". Da bi se kupile knjige korisnik pokrece PlacanjeActivity klikom na "zavrsi kupovinu". Ta aktivnost sumira sve knjige koje je odabrao,sabira cenu i na korisniku je da potvrdi.
Nakon potvrde uplata se obradjuje i kada je zavrsena obrada dobija notifikaciju za porukom o tome. Klik na notifikaciju salje korisnika na tab2. Dugim pritiskom na tab2 korisnik dobija pregled o kupljenim knjigama.
