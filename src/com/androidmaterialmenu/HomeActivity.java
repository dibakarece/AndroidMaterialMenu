package com.androidmaterialmenu;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnCloseListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.androidmaterialmenu.SlideMenuAdapter.viewCLickEvent;

public class HomeActivity extends ActionBarActivity implements viewCLickEvent{

	
	private String NAME = "";
	private String EMAIL = "";
	private int PROFILE = R.drawable.ic_profilepic;

    private Toolbar toolbar;                            
    private RecyclerView mRecyclerView;                         
    private SlideMenuAdapter mAdapter;                   
    private RecyclerView.LayoutManager mLayoutManager;            
    private DrawerLayout Drawer;                                
    private SearchView searchView = null;
    
    private ActionBarDrawerToggle mDrawerToggle;               
    private Context mContext;

    public static String TITLES[] = {"My Profile","My Message","Important Mail","Settings","Logout"};
	public static int ICONS[] = {R.drawable.ic_profile,R.drawable.ic_mypost,R.drawable.ic_impmail,R.drawable.ic_settings,R.drawable.ic_logout};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		 mContext=HomeActivity.this;
	     initilizActionBarDrawer();
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		MenuItem searchItem = menu.findItem(R.id.action_search);
		if (searchItem != null) {
			searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		}
		
		if (searchView != null) {
			searchView.setIconifiedByDefault(true);
			searchView.setQueryHint("Search job");
			searchView.setOnQueryTextListener(new OnQueryTextListener() {
				
				@Override
				public boolean onQueryTextSubmit(String s) {
					searchView.clearFocus();
				    ShowToast(mContext, "SerachText : " + s);
					return true;
				}
				
				@Override
				public boolean onQueryTextChange(String arg0) {
					return false;
				}
			});

			searchView.setOnSearchClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					closeDraware();
				}
			});
			
			MenuItemCompat.setOnActionExpandListener(searchItem, new OnActionExpandListener() {
			    @Override
			    public boolean onMenuItemActionCollapse(MenuItem item) {
			        return true;  
			    }

			    @Override
			    public boolean onMenuItemActionExpand(MenuItem item) {
			        return true; 
			    }
			});
			
			searchView.setOnCloseListener(new OnCloseListener() {
				
				@Override
				public boolean onClose() {
					return false;
				}
			});
		}
		return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }else if (id == R.id.action_settings) {
        	return true;
		}
        return super.onOptionsItemSelected(item);
    }
    
    @Override
	public void OnViewItemClick(int position) {
		switch (position) {

		case 1:
			closeDraware();
			break;
		case 2:
			closeDraware();
			break;
		case 3:
			closeDraware();
			break;
		case 4:
			closeDraware();
			break;
		case 5:
			closeDraware();
			break;

		default:
			break;
		}
	}
    
    private void initilizActionBarDrawer() {
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		toolbar.setTitle("My App");
		setSupportActionBar(toolbar);

		mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
		mRecyclerView.setHasFixedSize(true);
		
		mAdapter = new SlideMenuAdapter(HomeActivity.this,TITLES, ICONS, NAME, EMAIL,PROFILE);
		mAdapter.setViewclickevent(this);
		mRecyclerView.setAdapter(mAdapter);
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);

		Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
		mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar,
				R.string.openDrawer, R.string.closeDrawer) {

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);

			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

		};
		Drawer.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();
	}
    
    private void closeDraware(){
    	if (Drawer.isDrawerOpen(GravityCompat.START)) {
			Drawer.closeDrawers();
		}
    }
    
	/**
	 * Show Alert Toast message.
	 */
	public static void ShowToast(Context context,String msg){
		Toast mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		mToast.setGravity(Gravity.CENTER, 0, 0);
		mToast.show();
	}
}
