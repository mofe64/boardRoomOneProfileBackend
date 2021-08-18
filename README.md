"# boardRoomOneProfileBackend" 

endpoints:
- /profiles
- /profiles/upload

## /profiles
- method : POST
- accepts: form-data with param { file: imageFile}
- return: {profileImageUrl: uploaded image Url}


## /profiles/upload
- method: POST
- accepts: request body object {base64Url: base64EncodedImage}
- return: {profileImageUrl: uploaded image Url}
