package com.heaven.model.entity.update;


import android.os.Parcel;
import android.os.Parcelable;

public class VersionEntity implements Parcelable{
	public String version;
	public String url;
	public String urlUpdatePatch;
	public String urlBugFixPatch;
	public String content;
	public int versionCode;
	public int updatePatchVersionCode;
	public int bugFixedPatchVersionCode;



	@Override
	public String toString() {
		return "VersionEntity{" +
				"version='" + version + '\'' +
				", updatePatchVersionCode='" + updatePatchVersionCode + '\'' +
				", bugFixedPatchVersionCode='" + bugFixedPatchVersionCode + '\'' +
				", url='" + url + '\'' +
				", urlUpdatePatch='" + urlUpdatePatch + '\'' +
				", urlBugFixPatch='" + urlBugFixPatch + '\'' +
				", content='" + content + '\'' +
				", versionCode=" + versionCode +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.version);
		dest.writeString(this.url);
		dest.writeString(this.urlUpdatePatch);
		dest.writeString(this.urlBugFixPatch);
		dest.writeString(this.content);
		dest.writeInt(this.versionCode);
		dest.writeInt(this.updatePatchVersionCode);
		dest.writeInt(this.bugFixedPatchVersionCode);
	}

	public VersionEntity() {
	}

	protected VersionEntity(Parcel in) {
		this.version = in.readString();
		this.url = in.readString();
		this.urlUpdatePatch = in.readString();
		this.urlBugFixPatch = in.readString();
		this.content = in.readString();
		this.versionCode = in.readInt();
		this.updatePatchVersionCode = in.readInt();
		this.bugFixedPatchVersionCode = in.readInt();
	}

	public static final Creator<VersionEntity> CREATOR = new Creator<VersionEntity>() {
		@Override
		public VersionEntity createFromParcel(Parcel source) {
			return new VersionEntity(source);
		}

		@Override
		public VersionEntity[] newArray(int size) {
			return new VersionEntity[size];
		}
	};
}
