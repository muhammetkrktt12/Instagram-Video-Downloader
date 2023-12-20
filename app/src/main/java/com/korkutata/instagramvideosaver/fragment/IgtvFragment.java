package com.korkutata.instagramvideosaver.fragment;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.korkutata.instagramvideosaver.R;
import com.korkutata.instagramvideosaver.model.MainUrl;

import org.apache.commons.lang3.StringUtils;

public class IgtvFragment extends Fragment {

    String URL = "NULL";
    VideoView mparticularigtv;
    EditText getigtvLink;
    Button getigTv;
    Button downloadigtv;
    private MediaController mediaController;
    String igtvUrl = "1";
    private Uri uri2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.igtv_fragment,container,false);
        getigtvLink = view.findViewById(R.id.getigtvlink);
        getigTv = view.findViewById(R.id.getigtv);
        downloadigtv = view.findViewById(R.id.downloadigtv);
        mparticularigtv = view.findViewById(R.id.particularigtv);
        mediaController = new MediaController(getContext());
        mediaController.setAnchorView(mparticularigtv);


        getigTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                URL = getigtvLink.getText().toString().trim();

                if(getigtvLink.equals("NULL")) {

                    Toast.makeText(getContext(), "Please Enter URL", Toast.LENGTH_SHORT).show();
                }

                else {

                    String result2 = StringUtils.substringBefore(URL,"/?");

                    URL = result2 + "/?__a=1";
                    processdata();
                }

            }
        });
        downloadigtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!igtvUrl.equals("1")) {

                    DownloadManager.Request request = new DownloadManager.Request(uri2);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                    request.setTitle("Download");
                    request.setDescription(".............");
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM,"" + System.currentTimeMillis() + ".mp4");
                    DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
                    Toast.makeText(getContext(),"Downloaded",Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(getContext(), "No Video Download", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }

    private void processdata() {

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                MainUrl mainUrl = gson.fromJson(response, MainUrl.class);
                igtvUrl = mainUrl.getGraphql().getShort_media().getVideo_url();
                uri2 = Uri.parse(igtvUrl);
                mparticularigtv.setMediaController(mediaController);
                mparticularigtv.setVideoURI(uri2);
                mparticularigtv.requestFocus();
                mparticularigtv.start();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Not Able To Fetch", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

    }
}
