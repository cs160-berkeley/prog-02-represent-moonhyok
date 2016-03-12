package com.cs160.joleary.catnip;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    private static final String[] SHAKE = {"6610","19141","51016","49813","97146","20658","3077","48006","3060","37656","41204","89031","52773","56073","42718","79711","62690","43111","76561","19403","37421","33906","59314","97078","31722","48157","71410","67831","6751","60472","52248","25637","40583","98562","5603","92004","95382","68007","61858","81432","97455","55750","96701","77514","28306","90023","63742","91710","63730","1226","30821","21220","10313","73650","20636","70647","78634","58475","35615","45469","97114","20230","67143","50651","22408","65610","29418","27801","10282","77049","72216","11120","72011","15277","54499","85712","29709","30671","68327","35632","20690","60450","66450","87933","30547","92706","28602","92553","74834","84751","13290","25118","62341","61349","51445","84334","93244","82073","45107","16654","31643","17810","51452","67057","56034","72081","50168","15430","53546","25645","33019","15033","35551","57337","38582","51340","97215","61108","91350","1840","80542","75933","6245","45033","21715","39846","47359","58727","18254","71767","23702","72150","24256","42402","96019","44439","76268","3455","23462","16333","54204","40177","79560","52642","64449","86514","79544","22473","32957","20540","93381","47553","17354","70436","67447","14038","49892","19486","39040","63623","95746","71046","34649","1360","56222","41311","1702","31095","93277","72181","31139","94252","82221","11760","98020","22460","72295","79831","62462","8240","27009","59538","93232","47902","99363","38827","85347","48812","2861","51436","75083","27569","19176","25943","74082","28729","72378","2446","94115","81084","2471","41902","55396","78942","41052","56389","47838","70458","86334","29020","67544","36351","61920","78215","22436","40146","26591","88124","24143","78344","47735","32105","29933","96943","36919","32173","37068","44429","43337","78638","71048","91732","49039","64840","39169","72075","95209","62957","95555","53571","54027","99658","73442","12720","38011","63141","45262","64420","46120","61501","28563","60133","81643","54470","24326","98036","43981","49227","72845","40311","28232","49337","53001","80937","30506","28652","45505","83723","28147","5777","63869","70097","21210","54888","95968","18002","29116","72137","29052","55355","2153","32899","55705","49276","93601","85757","82201","77095","12588","43782","50574","15253","2117","10536","92530","95114","43947","12952","31015","97451","95318","49416","70403","75417","90201","32066","33742","22833","57382","77903","58121","74129","3745","53545","48242","20722","3884","14266","22101","16622","28612","37380","7502","95008","32655","48297","96027","97818","48023","76803","99587","16321","24472","63128","19899","63848","40997","55102","35601","51531","88118","55458","95409","49412","6140","67124","59865","22207","76015","18346","22824","71764","98083","13738","14208","78164","16841","99841","19022","32530","6161","79098","61257","1054","78781","89132","12919","70552","25231","85205","43115","97281","8859","90701","59065","74756","2445","29301","13094","50552","6454","38601","76652","32857","84710","96073","63870","36052","70470","37660","27888","59008","16852","42320","19119","88418","3042","20124","28467","38181","39066","29926","30149","22549","56186","90223","73022","22831","49883","60086","22959","87311","15437","93254","39762","46133","40510","64501","85123","32347","46958","47705","93610","38377","98203","8411","18705","78650","41081","81426","26259","40936","26335","14884","45206","8212","50013","26588","48068","46721","37212","39522","46818","38662","62675","65260","66741","43310","31820","62027","71828","51365","53201","99302","31524","95133","69201","93631","40283","18067","5442","15732","6042","84127","62853","66049","69135","75845","22332","8542","13687","28687","46360","76802","94177","53149","60064","66634","6020","17970","12766","12448","8037","11855","6335","24897","4674","29581","73770","29850","86002","17061","52654","59425","53078","91911","73561","52758","17859","53744","28021","21012","33111","78664","51601","94141","73115","27879","32815","29829","58479","20622","71166","22003","36784","14052","94062","74339","25907","77808","55321","27108","57770","90018","3602","77265","5045","32776","99110","32540","70390","25148","29506","57630","63110","78382","38620","24092","29206","33148","48061","45245","75835","84747","59751","1841","2723","50311","56167","4770","95613","78254","53046","52638","95433","13736","30221","36759","28285","26175","34660","71720","62098","66111","47244","8350","71038","36053","63167","58401","22724","78025","50836","78756","44253","74016","52220","6146","71747","15090","22130","94525","90717","28052","66727","28390","61775","37412","48821","65617","70454","48415","98366","30128","60296","35131","52221","52320","33619","10114","68414","54618","61027","50047","43057","8735","29375","6910","10528","2568","61070","90742","64030","75008","49121","72324","54853","37810","38963","41749","4609","73031","94506","40595","24067","77536","68445","95485","25437","65715","66403","58571","90042","47635","43044","43344","92241","21890","45618","72829","95202","71862","15688","78404","53942","55801","39181","25213","98166","84533","10124","50393","30293","33804","78014","95528","79920","33941","7016","92684","98239","21047","73098","33755","81069","56647","92201","98443","72417","81233","43733","52583","4541","65250","49865","50438","25727","36775","11248","18051","39167","46858","46012","62285","87059","61130","76273","91797","60627","55576","38850","43410","82422","82939","60068","37135","23899","12472","35459","76303","57003","67134","3872","94579","23884","16351","45309","97032","85137","38057","73549","22931","30110","35098","97835","57101","32777","71612","67351","76388","15046","79744","95945","57010","12514","40033","78069","80206","98243","59033","77643","75431","58505","48893","2349","31308","85737","60129","65345","79960","37301","54110","73744","93435","78702","3835","99827","37144","62417","75757","86329","31208","50627","18055","40803","53916","55946","79404","86343","30344","58224","56013","17734","20222","84774","70533","54819","20001","38131","84654","13808","6615","31832","39635","32812","79768","23127","54969","67837","1601","64078","92280","17067","21843","45896","73724","77545","65573","50476","11950","97045","20051","70808","76136","97751","28215","43125","36009","42082","6850","72189","23486","50177","95221","57717","36304","19074","18036","59017","20064","92227","91977","4563","51341","78135","37406","60026","60540","70461","47939","95203","41861","79911","60456","32710","82638","19311","38481","61790","83637","49615","24161","37339","65038","1243","45013","62876","26503","55957","79257","85297","94596","67432","41607","34788","78379","94913","6828","66507","47024","91933","14903","58602","2873","92249","5459","66422","11947","57049","17017","92260","27395","67102","25667","84126","8341","70540","83354","19460","31623","28721","62852","60484","18923","74019","2672","95431","22304","61281","42027","48724","4637","89705","14481","98856","20670","51345","46565","31145","43791","70535","87736","74701","97377","14703","60021","59003","61359","78256","14066","13156","41616","35249","8876","71034","17255","46298","24221","83536","22030","48187","92026","68863","38337","78720","60612","57301","6053","65741","99109","99737","58545","25064","20131","55144","33021","8833","61735","99701","95686","79852","72752","49807","4616","23060","87042","62661","79528","23911","29409","47985","61237","68969","64677","1740","34139","53569","95421","13123","94531","24872","83238","51029","35816","70706","58723","74466","37243","14588","30607","93673","94067","31739","99037","90809","49273","65350","52766","55110","68970","16246","47918","75173","67114","48615","32290","77053","92562","71441","36462","2093","44473","32963","38674","57429","74735","14817","6022","17009","70837","46720","35071","48166","7717","56058","31180","65679","85008","88113","98276"};


    private Button mElectBtn;
    String[] senate_1_info;
    Boolean clickable = false;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    MyGridPagerAdapter mAdapter;
    Intent intent;
    public Boolean alreadyShaken = false;
    private BoxInsetLayout mMain;

    private static final String Republican = "#EB5757";
    private static final String Democrat = "#2F80ED";
    private static final String Independent = "#BDBDBD";

    String county;

    String election_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DotsPageIndicator mPageIndicator;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

//
        intent = getIntent();
        Bundle extras = intent.getExtras();




//


        if (extras != null) {
            clickable = true;

            mPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);

            // Assigns an adapter to provide the content for this pager
            final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
            mAdapter = new MyGridPagerAdapter(this, getFragmentManager(), intent);


            county = extras.getString("county");

            election_data = extras.getString("election_data");

            mMain = (BoxInsetLayout) findViewById(R.id.main_view);

            String temp = extras.getString("senate_1");
            String tempParty = temp.split("@")[1];

            if (tempParty.matches(Republican)) {
                mMain.setBackgroundColor(Color.parseColor(Republican));

            } else if (tempParty.matches(Democrat)) {

                mMain.setBackgroundColor(Color.parseColor(Democrat));

            }


            pager.setAdapter(mAdapter);

            mPageIndicator.setPager(pager);
        }

        Button mElectBtn = (Button) findViewById(R.id.elect);
//        mBackgnd = (LinearLayout) findViewById(R.id.background);
//        mName = (TextView) findViewById(R.id.name);
//        mPic = (ImageView) findViewById(R.id.pic);



        mElectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (county != null) {
                    Intent sendIntent = new Intent(v.getContext(), ElectionView.class);
                    sendIntent.putExtra("loc", county);
                    sendIntent.putExtra("election_data", election_data);
                    startActivity(sendIntent);
                } else{
                    Toast.makeText(MainActivity.this, "Enter a location first.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void onClick(View v) {
        Intent sendIntent = new Intent(MainActivity.this, WatchToPhoneService.class);
//        TextView mName = (TextView) v.findViewById(R.id.cand_name);
//        Log.d("nameSent",mName.getText()+" is sent when clicked.");
//        sendIntent.putExtra("name",mName.getText());
//        sendIntent.putExtra("party",);
//        Toast.makeText(MainActivity.this, "Ouch you pressed me!?", Toast.LENGTH_LONG).show();

        GridViewPager mPage = (GridViewPager) findViewById(R.id.pager);
        Point mCoord = (Point) mPage.getCurrentItem();
        String mName = mAdapter.getTitle(mCoord.y, mCoord.x);
        String mParty = mAdapter.getParty(mCoord.y, mCoord.x);

        String mEnd = mAdapter.getEnd(mCoord.y, mCoord.x);
        String mBioguide = mAdapter.getBioguide(mCoord.y, mCoord.x);
        String mTweet = mAdapter.getTweet(mCoord.y, mCoord.x);

        sendIntent.putExtra("change", false);
        sendIntent.putExtra("name", mName);
        sendIntent.putExtra("party", mParty);
        sendIntent.putExtra("end", mEnd);
        sendIntent.putExtra("bioguide", mBioguide);
        sendIntent.putExtra("tweet", mTweet);

        Log.d("tweet in mainW",mTweet);

        startService(sendIntent);
//        Toast.makeText(MainActivity.this, "Please write in at least one", Toast.LENGTH_LONG).show();
    }


    private int mAccel = 0; // acceleration apart from gravity
    private int mAccelCurrent = 0; // current acceleration including gravity
    private int mAccelLast = 0; // last acceleration including gravity

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccel;
            mAccelCurrent = (int) Math.sqrt((double) (x * x + y * y + z * z));
            mAccel = mAccelCurrent - mAccelLast;
//            Log.d("sensor", "mAccel: "+Integer.toString(mAccel)+"mAccelCurrent: "+Integer.toString(mAccelCurrent)+"mAccelLast: "+Integer.toString(mAccelLast)+" Sensor changed. x: "+Float.toString(x)+" y: "+ Float.toString(y)+" z: "+Float.toString(z));
            if (mAccel > 20 && !alreadyShaken) {
//                Intent intent
                alreadyShaken = true;
                Log.d("Sensed!", "sensor reading" + mAccel);
                changeEverything();
            }
//            mAccel = mAccel * 0.9f + delta * 0.1f; // perform low-cut filter
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private void changeEverything() {

        Intent redoPhone = new Intent(getBaseContext(), WatchToPhoneService.class);

//        int idx = new Random().nextInt(SHAKE.length);

        String zipcode = SHAKE[(int)(System.currentTimeMillis() % SHAKE.length)];

        redoPhone.putExtra("zipcode", zipcode);
        redoPhone.putExtra("change", true);
        redoPhone.putExtra("cameFromWatch", "true");
        startService(redoPhone);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorListener);
    }



}



