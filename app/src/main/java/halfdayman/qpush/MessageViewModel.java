package halfdayman.qpush;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

public class MessageViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private LiveData<List<SquareMessage>> mMessages = new MutableLiveData<List<SquareMessage>>();
    private SquareMessageDao message;
    MySingleton mySingleton;

    public MessageViewModel(@NonNull Application application)
    {
        super(application);

        // Instantiate the RequestQueue.
        mySingleton = MySingleton.getInstance(application);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //application.deleteDatabase("square_message_database"); // for test only, should remove later
                message = SquareMessageDatabase.getInstance(application).squareMessage();
                mMessages =  message.getAll();
                SquareMessage squareMessage = message.findLatestMessage();
                updateMessages(squareMessage.id);
            }
        });


    }

    public void postMessage(String text) {
        Random r = new Random();
        SquareMessage squareMessage = new SquareMessage(-r.nextInt(100), "userA", text);
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                message.insertOneMessage(squareMessage);
            }
        });

        String url ="https://pkumozzie.cn/qpush/post-message";

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", "userA");
        params.put("message", text);

        JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.v("Response", response.toString(4));
                            Executors.newSingleThreadExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    SquareMessage squareMessage = message.findLatestMessage();
                                    updateMessages(squareMessage.id);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Volley Error","That didn't work!");
            }
        });

        req.setRetryPolicy(new DefaultRetryPolicy(10000, 0, 1.0f));

        // Add the request to the RequestQueue.
        mySingleton.addToRequestQueue(req);
    }

    public void updateMessages(int id) {
        Log.v("Volley Log","the id is" + id);
        String url ="https://www.pkumozzie.cn/qpush/get-messages?id=" + id;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        Log.v("Volley Response", "new response");
                        for(int i = 0; i < response.length(); i++){
                            try {
                                jsonObject = (JSONObject) response.get(i);
                                SquareMessage squareMessage = new SquareMessage(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("author"),
                                        jsonObject.getString("text"));
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        message.insertOneMessage(squareMessage);
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                message.deleteTemporaryMessages();
                            }
                        });

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Volley Error","That didn't work!");
                    }
                });
        // Add a request (in this example, called stringRequest) to your RequestQueue.
        mySingleton.addToRequestQueue(jsonArrayRequest);
    }

    public LiveData<List<SquareMessage>> getMessages() {
        return mMessages;
    }

}