package com.example.android.sofraa.data.model.service_api;

import com.example.android.sofraa.data.model.accept_delivery_order.AcceptDeliveryResponse;
import com.example.android.sofraa.data.model.accept_order.AcceptOrderResponse;
import com.example.android.sofraa.data.model.add_new_item_shop.AddNewItemShopResponse;
import com.example.android.sofraa.data.model.add_new_offer_shop.AddNewOfferShopResponse;
import com.example.android.sofraa.data.model.add_review.AddReviewResponse;
import com.example.android.sofraa.data.model.categories_list.CategoriesResponse;
import com.example.android.sofraa.data.model.cities.CitiesResponse;
import com.example.android.sofraa.data.model.commission.CommissionResponse;
import com.example.android.sofraa.data.model.contact_us.ContactUsResponse;
import com.example.android.sofraa.data.model.list_of_restaurant_items.ListOfRestaurantItemsResponse;
import com.example.android.sofraa.data.model.list_of_restaurants.UserListOfRestaurantResponse;
import com.example.android.sofraa.data.model.login.UserLoginResponse;
import com.example.android.sofraa.data.model.notify.UserNotifyResponse;
import com.example.android.sofraa.data.model.offer_details.OfferDetailsResponse;
import com.example.android.sofraa.data.model.offer_list_shop.AddOfferShopListResponse;
import com.example.android.sofraa.data.model.offers.OffersResponse;
import com.example.android.sofraa.data.model.region.RegionResponse;
import com.example.android.sofraa.data.model.register.UserRegisterResponse;
import com.example.android.sofraa.data.model.reset_password.UserNewPassResponse;
import com.example.android.sofraa.data.model.reset_password.UserResetPassResponse;
import com.example.android.sofraa.data.model.restaurant_details.RestaurantDetailsResponse;
import com.example.android.sofraa.data.model.restaurant_orders.RestaurantOrdersResponse;
import com.example.android.sofraa.data.model.restaurant_reviews.RestaurantReviewsResponse;
import com.example.android.sofraa.data.model.shop_login.LoginShopResponse;
import com.example.android.sofraa.data.model.shop_offers.OffersShopResponse;
import com.example.android.sofraa.data.model.shop_offers_new.ShopOffersResponse;
import com.example.android.sofraa.data.model.shop_products.ProductsResponse;
import com.example.android.sofraa.data.model.shop_register.ShopRegisterResponse;
import com.example.android.sofraa.data.model.user_my_orders.UserOrdersResponse;
import com.example.android.sofraa.data.model.user_offers.UserOffersResponse;
import com.example.android.sofraa.data.model.user_register_token.RegisterTokenResponse;
import com.example.android.sofraa.settings_media.SettingsMediaResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface API {

//    @FormUrlEncoded
//    @POST("login")
//    Call<LoginResponse> setLogin(@Field("email") String email,
//                                 @Field("password") String password);
//
//
//    @FormUrlEncoded
//    @POST("register")
//    Call<LoginResponse> setRegister(@Field("email") String email,
//                                    @Field("name") String name,
//                                    @Field("password") String password,
//                                    @Field("password_confirmation") String password_confirmation,
//                                    @Field("phone") String phone,
//                                    @Field("address") String address,
//                                    @Field("region_id") String region_id,
//                                    @Field("categories[]") ArrayAdapter<String> categories[][],
//                                    @Field("delivery_method_id") String delivery_method_id,
//                                    @Field("delivery_period") String delivery_period,
//                                    @Field("delivery_days") String delivery_days,
//                                    @Field("delivery_cost") String delivery_cost,
//                                    @Field("minimum_charger") String minimum_charger,
//                                    @Field("photo") String photo,
//                                    @Field("availability") String availability,
//                                    @Field("times[]") ArrayList<String> times[]);

    @FormUrlEncoded
    @POST("login")
    Call<UserLoginResponse> setUserLogin(@Field("email") String email,
                                         @Field("password") String password);


    @FormUrlEncoded
    @POST("register")
    Call<UserRegisterResponse> getUserRegister(@Field("name") String name,
                                               @Field("email") String email,
                                               @Field("password") String password,
                                               @Field("password_confirmation") String password_confirmation,
                                               @Field("phone") String phone,
                                               @Field("address") String address,
                                               @Field("region_id") int region_id);

    @GET("my-orders")
    Call<UserOrdersResponse> getUserOrders(@Query("api_token") String api_token,
                                           @Query("page") int page);

    @GET("notifications")
    Call<UserNotifyResponse> getNotifivations(@Query("api_token") String api_token,
                                              @Query("page") int page
    );

    @FormUrlEncoded
    @POST("reset-password")
    Call<UserResetPassResponse> ResetPass(@Field("email") String email);


    @FormUrlEncoded
    @POST("new-password")
    Call<UserNewPassResponse> NewPass(@Field("code") String code,
                                      @Field("password") String password,
                                      @Field("password_confirmation") String password_confirmation);


    @GET("restaurants")
    Call<UserListOfRestaurantResponse> getResturants(@Query("page") int page);

    @Multipart
    @POST("register")
    Call<ShopRegisterResponse> getShopRegisterResponse(@Part("name") RequestBody name,
                                                       @Part("email") RequestBody email,
                                                       @Part("password") RequestBody pass,
                                                       @Part("password_confirmation") RequestBody confirm_pass,
                                                       @Part("phone") RequestBody phone,
                                                       @Part("whatsapp") RequestBody whatsapp,
                                                       @Part("region_id") RequestBody region,
                                                       @Part("categories[]") List<RequestBody> categories,
                                                       @Part("delivery_cost") RequestBody delivery_cost,
                                                       @Part("minimum_charger") RequestBody minimum_charger,
                                                       @Part MultipartBody.Part photo,
                                                       @Part("availability") RequestBody availability);

    @GET("my-items")
    Call<ProductsResponse> getProductsResponse(@Query("api_token") String api_token,
                                               @Query("page") int page);

    @GET("commissions")
    Call<CommissionResponse> getCommissionResponse(@Query("api_token") String api_token);

    @GET("cities")
    Call<CitiesResponse> getCitiesResponse();

    @GET("regions")
    Call<RegionResponse> getRegionResponse(@Query("city_id") int city_id);

    @GET("categories")
    Call<CategoriesResponse> getCategoriesResponse();

    @Multipart
    @POST("new-item")
    Call<AddNewItemShopResponse> getAddNewItemShopResponse(@Part("description") RequestBody description,
                                                           @Part("price") RequestBody price,
                                                           @Part("preparing_time") RequestBody preparing_time,
                                                           @Part("name") RequestBody productname,
                                                           @Part MultipartBody.Part photo,
                                                           @Part("api_token") RequestBody api_token);

    @FormUrlEncoded
    @POST("login")
    Call<LoginShopResponse> getLoginShopResponse(@Field("email") String email,
                                                 @Field("password") String password);

    @Multipart
    @POST("new-offer")
    Call<AddNewOfferShopResponse> getAddNewOfferShopResponse(@Part("description") RequestBody description,
                                                             @Part("price") RequestBody price,
                                                             @Part("starting_at") RequestBody starting_at,
                                                             @Part("name") RequestBody name,
                                                             @Part MultipartBody.Part photo,
                                                             @Part("ending_at") RequestBody ending_at,
                                                             @Part("api_token") RequestBody api_token);

    @GET("my-offers")
    Call<AddOfferShopListResponse> getOffersListDataCall(@Query("api_token") String api_token);

    @GET("items")
    Call<ListOfRestaurantItemsResponse> getFoodList(@Query("restaurant_id") int restaurant_id,
                                                    @Query("page") int page);

    @GET("reviews")
    Call<RestaurantReviewsResponse> getRestaurantReviewsResponse(@Query("api_token") String api_token,
                                                                 @Query("restaurant_id") int restaurant_id,
                                                                 @Query("page") int page);

    @GET("offers")
    Call<UserOffersResponse> getUserOffersResponse();

    @FormUrlEncoded
    @POST("contact")
    Call<ContactUsResponse> getContactUsResponse(@Field("name") String name,
                                                 @Field("email") String email,
                                                 @Field("phone") String phone,
                                                 @Field("type") String type,
                                                 @Field("content") String content);

    @GET("settings")
    Call<SettingsMediaResponse> getSettingsMediaResponse(@Query("email") String email,
                                                         @Query("password") String password);

    //    @GET("my-orders")
//    Call<UserCurrentOrdersResponse>getUserCurrentOrdersResponse(
//            @Query("api_token") String api_token,
//            @Query("state") String state
//    );
    @GET("my-orders")
    Call<UserOrdersResponse> getUserOrdersResponse(@Query("api_token") String api_token,
                                                   @Query("state") String state,
                                                   @Query("page") int page);


    @GET("offer")
    Call<OfferDetailsResponse> getOfferDetailsResponse(@Query("offer_id") int offer_id);

    @GET("restaurant")
    Call<RestaurantDetailsResponse>getRestaurantDetailsResponse(@Query("restaurant_id") int restaurant_id);

    @GET("my-offers")
    Call<OffersResponse> getOffersResponse(
            @Query("api_token") String api_token
    );
    @GET("my-orders")
    Call<RestaurantOrdersResponse> getRestaurantOrdersResponse(
            @Query("api_token") String api_token,
            @Query("state") String state
    );
    @FormUrlEncoded
    @POST("accept-order")
    Call<AcceptOrderResponse>getAcceptOrderResponse(
            @Field("api_token") String api_token,
            @Field("order_id") int order_id
    );
    @FormUrlEncoded
    @POST("confirm-order")
    Call<AcceptDeliveryResponse> getAcceptDeliveryResponse(
            @Field("api_token") String api_token,
            @Field("order_id") int order_id
    );
    @FormUrlEncoded
    @POST("review")
    Call<AddReviewResponse> getAddReviewResponse(
            @Field("api_token") String api_token,
            @Field("rate") String rate,
            @Field("comment") String comment,
            @Field("restaurant_id") int restaurant_id
    );
    @FormUrlEncoded
    @POST("register-token")
    Call<RegisterTokenResponse> getRegisterTokenResponse(
            @Field("api_token") String api_token,
            @Field("token") String token,
            @Field("type") String type
    );
}
