var app = angular.module("WebSpotify", []);

//Controller Part
app.controller("ArtistController", function($scope, $http) {
    
    
    $scope.artists = [];
    $scope.artistForm = {
        id : -1,
        name : "",
        bio : ""
    };
    
    // load the data from server
    _refreshArtistData();
    
    $scope.submitArtist = function() {
        
        var method = "";
        var url = "";
        if ($scope.artistForm.id == -1) {
            // Id is absent in form data, it is new
            method = "POST";
            url = '/artist/add';
        } else {
            // Id is present in form data, it is an edit 
            method = "PUT";
            url = '/artist/add';
        }
        
        $http({
            method : method,
            url : url,
            data : angular.toJson($scope.artistForm),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then( _success, _error );
    };
    
    $scope.deleteArtist = function(artist) {
        $http({
            method : 'DELETE',
            url : '/artist/delete/' + artist.id
        }).then(_success, _error);
    };
    
    // In case of edit, populate form fields
    $scope.editArtist = function(artist) {
    
        $scope.artistForm.name = artist.name;
        $scope.artistForm.bio = artist.bio;
        $scope.artistForm.id = artist.id;
    };
    
    /* 'Private' functions */
    function _refreshArtistData() {
        $http({
            method : 'GET',
            url : '/artist/all'
        }).then(function successCallback(response) {
            $scope.artists = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
    
    function _success(response) {
        _refreshArtistData();
        _clearFormData()
    }
    
    function _error(response) {
        console.log(response.statusText);
    }
    
    function _clearFormData() {
        $scope.artistForm.id = -1;
        $scope.artistForm.name = "";
        $scope.artistForm.bio = "";
        
    };
});