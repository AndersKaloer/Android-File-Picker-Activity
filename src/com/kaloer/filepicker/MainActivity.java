/*
 * Copyright 2011 Anders Kalør
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kaloer.filepicker;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.filepicker.filepickeractivityproject.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private static final int REQUEST_PICK_FILE = 1;
	
	private TextView mFilePathTextView;
	private Button mStartActivityButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Set the views
        mFilePathTextView = (TextView)findViewById(R.id.file_path_text_view);
        mStartActivityButton = (Button)findViewById(R.id.start_file_picker_button);
        
        mStartActivityButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.start_file_picker_button:
			// Create a new Intent for the file picker activity
			Intent intent = new Intent(this, FilePickerActivity.class);
			
			// Set the initial directory to be the sdcard
			//intent.putExtra(FilePickerActivity.EXTRA_FILE_PATH, Environment.getExternalStorageDirectory());
			
			// Show hidden files
			//intent.putExtra(FilePickerActivity.EXTRA_SHOW_HIDDEN_FILES, true);
			
			// Only make .png files visible
			//ArrayList<String> extensions = new ArrayList<String>();
			//extensions.add(".png");
			//intent.putExtra(FilePickerActivity.EXTRA_ACCEPTED_FILE_EXTENSIONS, extensions);
			
			// Start the activity
			startActivityForResult(intent, REQUEST_PICK_FILE);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			switch(requestCode) {
			case REQUEST_PICK_FILE:
				if(data.hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
					// Get the file path
					File f = new File(data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH));
					
					// Set the file path text view
					mFilePathTextView.setText(f.getPath());
				}
			}
		}
	}
}