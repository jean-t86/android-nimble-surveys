# Nimble Surveys

## Description

I developed this Android app as part of the recruitment process when going through the hiring process at my old company, Nimble.

Below are the requirements and development notes that had to be met to successfully pass the coding challenge.

## Requirements

* Create an application that allows users to browse a list of surveys. For the UI, the application should look like the above screen.

* Integrate with an OAuth API to retrieve data (see Resources section).

* A survey card should display the following info:
    * Cover image (background)
    * Name (in bold)
    * Description

There should 2 actions:
* The refresh icon in the top-left corner should fetch the list of surveys and update
  the list of surveys displayed

* The button “Take Survey” should take the user to a new screen. Simply
  implement the navigation.

* The list of surveys should be fetched when opening the application

* The navigation indicator list (bullets) should be dynamic and based on the API response

## Methodology

* Develop the application using:
    * Android Studio
    * Gradle

* Supported Android SDKs: 5.0 up

* Use Git during the development process. Push to a public repository on Bitbucket, Github or Gitlab. Make regular commits and merge code using pull requests.

## Resources

Integrate with the following API (OAuth 2 API):

* Endpoint: https://nimbl3-survey-api.herokuapp.com/surveys.json

* Credentials:

Params name: access_token

Token: d9584af77d8c0d6622e2b3c554ed520b2ae64ba0721e52daa12d6eaa5e5cdd93

### Notes :

* To get the high resolution image just append “l” to the image url you obtain in the API
  response

* If you get timeout or it’s too slow you can paginate the results using page, per_page query params e.g.

https://nimbl3-survey-api.herokuapp.com/surveys.json?page=1&per_page=10

If the token is expired, a new one can be obtained via

https://nimbl3-survey-api.herokuapp.com/oauth/token

```
curl https://nimbl3-survey-api.herokuapp.com/oauth/token --data
"grant_type=password&username=carlos@nimbl3.com&password=antikera"
```

The response should be:
```
{"access_token":"d9584af77d8c0d6622e2b3c554ed520b2ae64ba0721e52da
a12d6eaa5e5cdd93","token_type":"bearer","expires_in":7200,"create
d_at":1485174186}
```
The logic to fetch the token should be part of the authentication logic i.e. do not hardcode the access token into the app as it expires.
