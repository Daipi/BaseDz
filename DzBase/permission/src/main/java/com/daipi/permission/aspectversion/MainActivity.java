package com.daipi.permission.aspectversion;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daipi.permission.R;
import com.daipi.permission.aspectversion.annotation.Permission;
import com.daipi.permission.aspectversion.annotation.PermissionCancel;
import com.daipi.permission.aspectversion.annotation.PermissionDenied;

/**
 * TODO 用户的角度上  use 我们写的权限申请库
 *
 * 你是架构师设计，你的组员用户，只需要面对三个注解就行了
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 不用反射，也不用 APT

        /*Xutils.xxx(this);  反射
        Dagger2.xxxx(this);  APT
        ARouter.xxx(this);  APT
        */
    }

    public void test(View view) {}

    // 点击事件
    public void permissionRequestTest(View view) {
        testRequest();
    }

    // 申请权限  函数名可以随意些
    @Permission(value = Manifest.permission.READ_EXTERNAL_STORAGE, requestCode = 200)
    public void testRequest() {
        Toast.makeText(this, "权限申请成功...", Toast.LENGTH_SHORT).show();
    }

    // 权限被取消  函数名可以随意些
    @PermissionCancel
    public void testCancel() {
        Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
    }

    // 多次拒绝，还勾选了“不再提示”
    @PermissionDenied
    public void testDenied() {
        Toast.makeText(this, "权限被拒绝(用户勾选了 不再提示)，注意：你必须要去设置中打开此权限，否则功能无法使用", Toast.LENGTH_SHORT).show();
    }
}
