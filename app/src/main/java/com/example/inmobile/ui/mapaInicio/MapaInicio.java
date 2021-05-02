package com.example.inmobile.ui.mapaInicio;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.inmobile.MapaInicioViewModel;
import com.example.inmobile.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaInicio extends Fragment {

    private GoogleMap map;
    private View mapaDetalle=null;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {

            map=googleMap;
            map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }


                @Override
                public View getInfoContents(Marker marker) {
                    if(mapaDetalle == null){
                        mapaDetalle = getLayoutInflater().inflate(R.layout.mapa_detalle,null);
                    }

                    ImageView iv = mapaDetalle.findViewById(R.id.ivInmo);
                    iv.setImageResource(R.drawable.inmobiliaria);


                    return (mapaDetalle);
                }

            });
            LatLng Inmobiliaria = new LatLng(-33.184008, -66.313448);

            map.addMarker(new MarkerOptions().position(Inmobiliaria).title("INMOBILE GROUP").icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador)).snippet("Lideres en el mercado inmobiliario"));
            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(Inmobiliaria)
                    .zoom(70)
                    .bearing(45)
                    .tilt(90)
                    .build();
            CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);
            map.animateCamera(camUpd);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mapa_inicio_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapaInicio);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }}