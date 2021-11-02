package su.rbv.planner5d.core_projects.data.net.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import su.rbv.planner5d.core_projects.BuildConfig
import su.rbv.planner5d.core_projects.data.net.NetApi
import su.rbv.planner5d.core_projects.domain.repository.NetRepository
import su.rbv.planner5d.core_projects.data.net.NetRepositoryImpl
import su.rbv.planner5d.core_projects.data.net.mapper.ProjectPreviewNetDataToDomainMapper
import javax.inject.Singleton

@Module
internal class NetRepositoryModule {

    @Provides
    @Singleton
    fun provideNetRepository(api: NetApi, mapper: ProjectPreviewNetDataToDomainMapper): NetRepository =
        NetRepositoryImpl(api, mapper)

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(context: Context): OkHttpClient.Builder =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addInterceptor(ChuckInterceptor(context))
        }

    @Provides
    @Singleton
    fun provideNetAPI(clientBuilder: OkHttpClient.Builder): NetApi =
        Retrofit.Builder()
            .client(clientBuilder.build())
            .baseUrl(NetApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetApi::class.java)
}