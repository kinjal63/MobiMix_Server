
package com.taqnihome.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.taqnihome.domain.AppData;
import com.taqnihome.domain.DeviceDetails;
import com.taqnihome.domain.GameData;
import com.taqnihome.domain.GameInfo;
import com.taqnihome.domain.ResponseGame;
import com.taqnihome.domain.User;
import com.taqnihome.domain.UserAvailablity;
import com.taqnihome.domain.UserConnectionInfo;
import com.taqnihome.domain.UserInfo;
import com.taqnihome.domain.UserInput;
import com.taqnihome.service.DeviceDetailsService;
import com.taqnihome.service.GameProfileService;
import com.taqnihome.service.UserAdminService;
import com.taqnihome.service.UserService;
import com.taqnihome.utils.Generator;

@RestController
public class HomeController {

    private final UserService userDataService;

//    private final GameLibraryService gameLibraryService;

    private final GameProfileService gameProfileService;

    private final DeviceDetailsService deviceDetailsService;

//    private final GameCategoryService gameCategoryService;

    private final UserAdminService userAdminService;

//    private final GameProfileTimeDetailsService gameProfileTimeDetailsService;

    @Autowired
    public HomeController(UserService userService, /*GameLibraryService gameLibraryService,*/
                          GameProfileService gameProfileService, DeviceDetailsService deviceDetailsService,
                          /*GameCategoryService gameCategoryService,*/ UserAdminService userAdminService/*, GameProfileTimeDetailsService gameProfileTimeDetailsService*/) {
        this.userDataService = userService;
//        this.gameLibraryService = gameLibraryService;
        this.gameProfileService = gameProfileService;
        this.deviceDetailsService = deviceDetailsService;
//        this.gameCategoryService = gameCategoryService;
        this.userAdminService = userAdminService;
//        this.gameProfileTimeDetailsService = gameProfileTimeDetailsService;
    }

//    @RequestMapping(value = "gameCategory", method = RequestMethod.POST)
//    public ResponseEntity<?> createGameCategory(@NotNull @RequestBody GameCategory gameCategory) {
//        try {
//
//            gameCategory.setGameCategoryId(Generator.generateUniqueId());
//            gameCategory.setCreatedDate(System.currentTimeMillis());
//            return ResponseEntity.ok(gameCategoryService.save(gameCategory));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

//    @RequestMapping(value = "gameCategory", method = RequestMethod.GET)
//    public ResponseEntity<?> getALlCategory() {
//        try {
//            return ResponseEntity.ok(gameCategoryService.getAll());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.noContent().build();
//        }
//    }

    @RequestMapping(value = "pushRegister", method = RequestMethod.POST)
    public ResponseEntity<?> saveDeviceData(@RequestBody DeviceDetails deviceDetails) {

        try {
            deviceDetails.setCreatedDate(System.currentTimeMillis());
            deviceDetailsService.save(deviceDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(deviceDetails);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity<?> saveData(@RequestBody User user) {
        try {
        	System.out.println("-------------------- Phase 1");
        	System.out.println("User name->" + user.getName());

            if (userDataService.findByEmail(user.getEmail()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
            }

        	System.out.println("-------------------- Phase 2");
            user.setCreationDate(System.currentTimeMillis());
            user.setId(Generator.generateUniqueId());
            
        	System.out.println("-------------------- Phase 3");
            DeviceDetails deviceDetails = deviceDetailsService.getById(user.getUserDeviceDetailses().get(0).getDeviceId());

        	System.out.println("-------------------- Phase 4");
            if (deviceDetails != null) {
                List<DeviceDetails> deviceDetailses = new ArrayList<>();
                deviceDetailses.add(deviceDetails);
                user.setUserDeviceDetailses(deviceDetailses);
            }
            
        	System.out.println("-------------------- Phase 5");

        	System.out.println("-------------------- Phase 6");
            user = userDataService.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Exception in signup" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User userData = userDataService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (userData == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Not found");
            }
            System.out.println(user.getUserDeviceDetailses() == null);

            User tempUser = userDataService.findByMacAddress(user.getMacAddress());
            if (tempUser != null) {
                if (!tempUser.getEmail().equals(userData.getEmail()) && !tempUser.getPassword().equals(userData.getPassword())) {
                    tempUser.setMacAddress("");
                    userDataService.save(tempUser);
                }
            }


            DeviceDetails deviceDetails = deviceDetailsService.getById(user.getUserDeviceDetailses().get(0).getDeviceId());
            userData.getUserDeviceDetailses().clear();
            userData.setMacAddress(user.getMacAddress());
            User temp = userDataService.save(userData);
            System.out.println("temp  " + temp.getUserDeviceDetailses().size());


            if (deviceDetails != null) {
                //  deviceDetailsService.delete(deviceDetails);
                List<DeviceDetails> deviceDetailses = new ArrayList<>();
                deviceDetailses.add(deviceDetails);
                userData.setUserDeviceDetailses(deviceDetailses);
            }
            userDataService.save(userData);
            return ResponseEntity.status(HttpStatus.OK).body(userData);

        } catch (Exception e) {
            System.out.println("Exception in login" + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ResponseEntity<?> checkMacAddress(@RequestBody User userdata) {
        try {
            System.out.println("mac address  " + userdata.getMacAddress() + "HEYY");
            User user = userDataService.findByMacAddress(userdata.getMacAddress());

            if (user != null) {
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Content");
        } catch (Exception e) {
            System.out.println("Exception in search" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

	/*
     * @RequestMapping(value = "uploadProfilePic")
	 * 
	 * @ResponseBody public String updateProfilePic(@RequestBody User user) {
	 * 
	 * return ""; }
	 */

//    @RequestMapping(value = "addGameToProfile", method = RequestMethod.POST)
//    public ResponseEntity<?> addGameToProfile(@RequestBody GameProfile gameProfile) {
//
//        try {
//            gameProfile.setGameProfileId(Generator.generateUniqueId());
//            gameProfile.setCreationDate(System.currentTimeMillis());
//            return ResponseEntity.status(HttpStatus.CREATED).body(gameProfileService.save(gameProfile));
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//
//    }

//    @RequestMapping(value = "updateGameToProfile", method = RequestMethod.PUT)
//    public ResponseEntity<?> updateGameToProfile(@RequestBody GameProfile gameProfile) {
//        try {
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(gameProfileService.save(gameProfile));
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

//    @RequestMapping(value = "addGameRequestToLibrary", method = RequestMethod.POST)
//    public ResponseEntity<?> addGameRequestToLibrary(@RequestBody GameProfile gameProfile) {
//        try {
//            GameLibrary gameLibraryObject = gameLibraryService
//                    .findByPackageName(gameProfile.getGameLibrary().getPackageName());
//
//            if (gameLibraryObject == null) {
//                GameLibrary gameLibrary = gameProfile.getGameLibrary();
//                gameLibrary.setCreationDate(System.currentTimeMillis());
//                gameLibrary.setGameId(Generator.generateUniqueId());
//                gameLibrary.setGooglePlayUrl(
//                        "https://play.google.com/store/apps/details?id=" + gameLibrary.getPackageName() + "&hl=en");
//                gameLibrary.setIsApproved(false);
//                gameLibrary.setCreationDate(System.currentTimeMillis());
//                gameLibrary.getGameCategoryId().setGameCategoryId("1");
//                gameLibrary.getGameCategoryId().setCategoryName(null);
//                gameLibrary.getGameCategoryId().setCreatedDate(System.currentTimeMillis());
//                gameLibrary.setGamePlatform("Android");
//                gameLibraryService.save(gameLibrary);
//                return ResponseEntity.status(HttpStatus.CREATED).body(gameLibrary);
//            } else {
//
//                return ResponseEntity.status(HttpStatus.CREATED).body(gameLibraryObject);
//            }
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//
//    }
//
//    @RequestMapping(value = "getAllLibraryGames", method = RequestMethod.GET)
//    public ResponseEntity<?> getAllGames() {
//        try {
//            return ResponseEntity.ok(gameLibraryService.getAll());
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//        }
//    }
//
//    @RequestMapping(value = "getGameProfileByUser", method = RequestMethod.GET)
//    public ResponseEntity<?> getAllProfileGames(@RequestParam String userId) {
//        try {
//
//            User user = new User();
//            user.setId(userId);
//            user = userDataService.getById(userId);
//            List<GameProfile> gameProfileList = user.getGameProfiles();
//            System.out.println(gameProfileList.size() + " before ");
//            // List<GameLibrary> gameLibraryListPackage =
//            // gameProfileList.stream().map(GameProfile::getGameLibrary).collect(Collectors.toList());
//            List<GameLibrary> gameLibraryList = gameLibraryService.findByIsApprovedFalseAndApprovedDateNotNull();
//            // gameLibraryListPackage.removeAll(gameLibraryList);
//            Predicate<GameProfile> predicate = p -> gameLibraryList.contains(p.getGameLibrary());
//            gameProfileList.removeIf(predicate);
//            System.out.println(predicate + "   predicate");
//
//            System.out.println(gameProfileList.size() + " after ");
//
//            return ResponseEntity.ok(gameProfileList);
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//        }
//
//    }
//
//    @RequestMapping(value = "getGamesForAddToProfile", method = RequestMethod.GET)
//    public ResponseEntity<?> getAllGamesWithoutGameProfiles(@RequestParam String userId,
//                                                            @RequestParam List<String> packageList) {
//        try {
//
//            List<GameLibrary> gameLibraryListPackage = gameLibraryService.getAll();
//            List<String> gamePackageNameList = gameLibraryListPackage.stream().map(GameLibrary::getPackageName)
//                    .collect(Collectors.toList());
//            packageList.removeAll(gamePackageNameList);
//            /*List<String> finalPackageList = new ArrayList<>();
//            finalPackageList.addAll(packageList);
//            finalPackageList.removeAll(gamePackageNameList);
//            System.out.println("2 : " + finalPackageList);
//            List<GameLibrary> gameLibraryList = gameLibraryService.findByIsApprovedFalse();
//            gamePackageNameList = gameLibraryList.stream().map(GameLibrary::getPackageName)
//                    .collect(Collectors.toList());
//            System.out.println("3 : " + gamePackageNameList);
//            finalPackageList.removeAll(gamePackageNameList);
//
//            long difference = getDiffYears(new Date(user.getDob()), new Date(System.currentTimeMillis()));
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(difference);
//            int year = calendar.get(Calendar.YEAR);
//
//            List<GameLibrary> ageRestrictionGameLibrary = gameLibraryService.findByAgeRatingLessThan(year);
//            gamePackageNameList = ageRestrictionGameLibrary.stream().map(GameLibrary::getPackageName).collect(Collectors.toList());
//            finalPackageList.removeAll(gamePackageNameList);
//            System.out.println("4 : " + finalPackageList);*/
//            return ResponseEntity.status(HttpStatus.OK).body(packageList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
//        }
//    }
//
//    @RequestMapping(value = "/requestedGameList", method = RequestMethod.GET)
//    public List<GameLibrary> getAllRequestedGameByUsers() {
//
//        try {
//            return gameLibraryService.findByIsApprovedFalseAndApprovedDateIsNull();
//        } catch (Exception e) {
//            // TODO: handle exception
//            return null;
//        }
//    }
//
//    @RequestMapping(value = "/getGameLibraryById", method = RequestMethod.GET)
//    public GameLibrary getGameLibraryById(@RequestParam("id") String id) {
//        try {
//            return gameLibraryService.findByGameId(id);
//        } catch (Exception e) {
//            // TODO: handle exception
//            return null;
//        }
//    }
//
//    @RequestMapping(value = "/gameDiscardedByAdmin", method = RequestMethod.GET)
//    public GameLibrary gameDiscardedByAdmin(@RequestParam("id") String id) {
//        try {
//            GameLibrary gameLibrary = gameLibraryService.getById(id);
//            gameLibrary.setApprovedDate(System.currentTimeMillis());
//            return gameLibraryService.save(gameLibrary);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    @RequestMapping(value = "/gameApprovedByAdmin", method = RequestMethod.POST)
//    public GameLibrary gameApprovedByAdmin(@RequestBody GameLibrary gameLibrary) {
//
//        try {
//            System.out.println("game Library id " + gameLibrary.getGameId());
//            if (gameLibrary.getGameId() != null && !gameLibrary.getGameId().trim().equals("")) {
//
//                List<GameProfile> gameProfileList = gameProfileService.findByGameLibrary(gameLibrary);
//                for (GameProfile gameProfile : gameProfileList) {
//                    int ageRatingGame = gameLibrary.getAgeRating();
//                    int yearDiff = getDiffYears(new Date(gameProfile.getUserId().getDob()), new Date(System.currentTimeMillis()));
//                    System.out.println("Year Difference " + yearDiff + " " + gameProfile.getGameProfileId());
//                    if (ageRatingGame > yearDiff) {
//
//                        gameProfileService.delete(gameProfile.getGameProfileId());
//
//                    }
//                }
//            }
//
//
//            if (gameLibrary.getGameId() == null || gameLibrary.getGameId().trim().equals("")) {
//                gameLibrary.setGameId(Generator.generateUniqueId());
//                gameLibrary.setCreationDate(System.currentTimeMillis());
//                gameLibrary.setGooglePlayUrl(
//                        "https://play.google.com/store/apps/details?id=" + gameLibrary.getPackageName() + "&hl=en");
//
//            }
//            gameLibrary.setGamePlatform("Android");
//            gameLibrary.setGameCategoryId(
//                    gameCategoryService.getById(gameLibrary.getGameCategoryId().getGameCategoryId()));
//            gameLibrary.setApprovedDate(System.currentTimeMillis());
//            gameLibrary.setApproved(true);
//            return gameLibraryService.save(gameLibrary);
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @RequestMapping(value = "gameUpdate", method = RequestMethod.POST)
//    public GameLibrary updateGameLibrary(@RequestBody GameLibrary gameLibrary) {
//        try {
//            gameLibrary.setGameCategoryId(
//                    gameCategoryService.getById(gameLibrary.getGameCategoryId().getGameCategoryId()));
//            gameLibrary.setIsApproved(true);
//            return gameLibraryService.save(gameLibrary);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }
//
//    @RequestMapping(value = "adminLogin", method = RequestMethod.POST)
//    public UserAdmin userAdminLogin(@RequestBody UserAdmin userAdmin) {
//        try {
//
//            return userAdminService.findByEmailAndPassword(userAdmin.getEmail(), userAdmin.getPassword());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }
//
//    @RequestMapping(value = "adminRegister", method = RequestMethod.POST)
//    public UserAdmin userAdminRegister(@RequestBody UserAdmin userAdmin) {
//        try {
//
//            if (userAdminService.findByEmail(userAdmin.getEmail()) != null) {
//                return userAdmin;
//            }
//            userAdmin.setId(Generator.generateUniqueId());
//            userAdmin.setCreationDate(System.currentTimeMillis());
//
//            return userAdminService.save(userAdmin);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }
//
//    @RequestMapping(value = "categoryUpdate", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    GameCategory updateGameCategory(@RequestBody GameCategory gameCategory) {
//        try {
//            return gameCategoryService.save(gameCategory);
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @RequestMapping(value = "categoryInsert", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    GameCategory insertGameCategory(@RequestBody GameCategory gameCategory) {
//        try {
//            gameCategory.setCreatedDate(System.currentTimeMillis());
//            gameCategory.setGameCategoryId(Generator.generateUniqueId());
//            return gameCategoryService.save(gameCategory);
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    @RequestMapping(value = "getNearByUserGames", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    ResponseEntity<?> getNearByUserGames(@RequestParam String userId, @RequestParam String bluetoothAddress,
//                                         @RequestParam List<String> packageList) {
//
//        System.out.println(userId + "  ----   " + bluetoothAddress + "     " + Arrays.toString(packageList.toArray()));
//        List<GameProfile> gameProfileList = null;
//        List<GameProfile> nearByUserProfileList = null;
//        System.out.println(" hello    found  here  ");
//        try {
//
//            gameProfileList = userDataService.getById(userId).getGameProfiles(); // list all games of user
//
//            List<GameLibrary> gameLibraryList = gameLibraryService.findByIsApprovedFalse(); // list all disapproved games
//            List<String> gamePackageNameList = gameLibraryList.stream().map(GameLibrary::getPackageName)
//                    .collect(Collectors.toList());
//            packageList.removeAll(gamePackageNameList);
//            List<String> checkFinalPackage = new ArrayList<>();
//
//            Iterator<GameProfile> gameProfileIterator = gameProfileList.iterator();
//            while (gameProfileIterator.hasNext()) {
//
//                GameProfile gameProfile = gameProfileIterator.next();
//                if (packageList.contains(gameProfile.getGameLibrary().getPackageName())) {
//                    // gameProfileIterator.remove();
//                    checkFinalPackage.add(gameProfile.getGameLibrary().getPackageName());
//                }
//            }
//
//            User userNearBy = userDataService.findByMacAddress(bluetoothAddress);
//
//            if (userNearBy != null) {
//
//
//                Long timeMillis = TimeUnit.HOURS.toMillis(new Date().getHours()) + TimeUnit.MINUTES.toMillis(new Date().getMinutes()) + TimeUnit.SECONDS.toMillis(new Date().getSeconds());
//                List<String> userIds = new ArrayList<>();
//                userIds.add(userNearBy.getId());
//                List<User> users = userDataService.findDistinctIdByIdInAndGameProfileTime_GameProfileStartTimeLessThanEqualAndGameProfileTime_GameProfileEndTimeGreaterThanEqual(userIds, timeMillis, timeMillis);
//
//                if (users.size() == 1) {
//                    nearByUserProfileList = userNearBy.getGameProfiles();
//
//                    Predicate<GameProfile> predicate = p -> gameLibraryList.contains(p.getGameLibrary());
//                    nearByUserProfileList.removeIf(predicate);
//
//
//                    Iterator<GameProfile> gameProfileIterator1 = nearByUserProfileList.iterator();
//
//                    while ((gameProfileIterator1.hasNext())) {
//
//                        GameProfile gameProfile = gameProfileIterator1.next();
//                        if (!checkFinalPackage.contains(gameProfile.getGameLibrary().getPackageName())) {
//                            gameProfileIterator1.remove();
//                        }
//                    }
//                    System.out.println(" heylo   " + bluetoothAddress + "  size  " + nearByUserProfileList.size());
//
//                    if (nearByUserProfileList.size() > 0) {
//                        User user = nearByUserProfileList.get(0).getUserId();
//                        user.setGameProfiles(nearByUserProfileList);
//                        return ResponseEntity.status(HttpStatus.OK).body(user);
//                    }
//                }
//
//
//            }
//
//
//            //  System.out.println(" heylo   " + bluetoothAddress + "  size  " + nearByUserProfileList.size());
//           /* if(gameProfileList.size() > 0 ){
//
//                User user =  gameProfileList.get(0).getUserId();
//                user.setGameProfiles(gameProfileList);
//                return ResponseEntity.status(HttpStatus.OK).body(user) ;
//
//            }
//*/
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//    }
//
//
//    @RequestMapping(value = "updateGamePlayingTime", method = RequestMethod.POST)
//    public ResponseEntity<?> updateGamePlayingTime(@RequestBody User userData) {
//
//        try {
//            /*User user = userDataService.getById(userData.getId());
//            user.setStartTime(userData.getStartTime());
//            user.setEndTime(userData.getEndTime());
//            userDataService.save(user);*/
//
//            GameProfileTimeDetails timeDetails = gameProfileTimeDetailsService.findByGameProfileTimeScheduleAndUser_Id(userData.getName(), userData.getId());
//            if (timeDetails == null) {
//                timeDetails = new GameProfileTimeDetails();
//                timeDetails.setGameProfileTimeId(Generator.generateUniqueId());
//            }
//            timeDetails.setGameProfileStartTime(userData.getStartTime());
//            timeDetails.setGameProfileEndTime(userData.getEndTime());
//            timeDetails.setGameProfileTimeSchedule(userData.getName());
//            timeDetails.setUserId(userData);
//            gameProfileTimeDetailsService.save(timeDetails);
//
//            return ResponseEntity.status(HttpStatus.OK).body(userDataService.getById(userData.getId()));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

//    @RequestMapping(value = "updateUserLocation", method = RequestMethod.POST)
//    public ResponseEntity<?> updateUserLocation(@RequestBody User userData) {
//        HashSet<String> gameHashSet = new HashSet<>();
//        try {
//            System.out.println(userData.getLatitude() + "     " + userData.getLongitude());
//            User user = userDataService.getById(userData.getId());
//            user.setLatitude(userData.getLatitude());
//            user.setLongitude(userData.getLongitude());
//            userDataService.save(user);
//            List<User> userList = userDataService.findByLocation(user.getLatitude(), user.getLongitude());
//            Long timeMillis = TimeUnit.HOURS.toMillis(new Date().getHours()) + TimeUnit.MINUTES.toMillis(new Date().getMinutes()) + TimeUnit.SECONDS.toMillis(new Date().getSeconds());
//            System.out.println("time millis " + timeMillis);
//            if (userList.size() > 1) {
//                System.out.println(" filter by location list " + userList.size());
//                List<String> userIds = userList.stream().map(User::getId).collect(Collectors.toList());
//                List<User> filterByTimeUserList = userDataService.findDistinctIdByIdInAndGameProfileTime_GameProfileStartTimeLessThanEqualAndGameProfileTime_GameProfileEndTimeGreaterThanEqual(userIds, timeMillis, timeMillis);
//                System.out.println("filter by time list " + filterByTimeUserList.size() + "     ");
//
//                if (filterByTimeUserList.contains(user) && filterByTimeUserList.size() > 1) {
//                    filterByTimeUserList.remove(user);
//                    List<GameLibrary> gameLibraries = user.getGameProfiles().stream().map(GameProfile::getGameLibrary).collect(Collectors.toList());
//                    /*List<GameLibrary> tempGameLibraryList = gameLibraryService.findByIsApprovedFalse();
//                    Predicate<GameLibrary> predicate = p -> tempGameLibraryList.contains(p);
//                    gameLibraries.removeIf(predicate);*/
//
//                    List<User> listNotificationUser = new ArrayList<>();
//                    System.out.println(Arrays.toString(gameLibraries.stream().map(GameLibrary::getPackageName).collect(Collectors.toList()).toArray()));
//                    for (User userDatas : filterByTimeUserList) {
//                        for (GameProfile gameProfile : userDatas.getGameProfiles()) {
//                            System.out.println(gameProfile.getGameLibrary().getPackageName());
//                            if (gameLibraries.contains(gameProfile.getGameLibrary())) {
//                                listNotificationUser.add(userDatas);
//                                gameHashSet.add(gameProfile.getGameLibrary().getGameName());
//                            }
//
//                        }
//                    }
//
//                    /*if (listNotificationUser.size() > 0) {
//
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                sendPushNotifications(listNotificationUser);
//                            }
//                        }).run();
//                    }*/
//
//                }
//            }
//            System.out.println(" Response  " + Arrays.toString(gameHashSet.toArray()));
//            return ResponseEntity.status(HttpStatus.OK).body(gameHashSet);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//
//
//    }
//
//
//    @RequestMapping(value = "getLocations", method = RequestMethod.GET)
//    public List<User> getLocations() {
//        int count = 0;
//
//        try {
//            User user = userDataService.getById("c2da8e95138a4bd596a683e201f2a49f");
//            List<User> userList = userDataService.findByLocation(12.9735414, 77.6366984);
//            Long timeMillis = TimeUnit.HOURS.toMillis(new Date().getHours()) + TimeUnit.MINUTES.toMillis(new Date().getMinutes()) + TimeUnit.SECONDS.toMillis(new Date().getSeconds());
//            System.out.println("time millis " + timeMillis + " date  " + new Date());
//            if (userList.size() > 1) {
//                System.out.println(" filter by location list " + userList.size() + " date  " + new Date());
//                List<String> userIds = userList.stream().map(User::getId).collect(Collectors.toList());
//                List<User> filterByTimeUserList = userDataService.findDistinctIdByIdInAndGameProfileTime_GameProfileStartTimeLessThanEqualAndGameProfileTime_GameProfileEndTimeGreaterThanEqual(userIds, timeMillis, timeMillis);
//                System.out.println("filter by time list " + filterByTimeUserList.size() + " date  " + new Date());
//                if (filterByTimeUserList.contains(user) && filterByTimeUserList.size() > 1) {
//                    filterByTimeUserList.remove(user);
//                    List<GameLibrary> gameLibraries = user.getGameProfiles().stream().map(GameProfile::getGameLibrary).collect(Collectors.toList());
//                    List<GameLibrary> tempGameLibraryList = gameLibraryService.findByIsApprovedFalse();
//                    Predicate<GameLibrary> predicate = p -> tempGameLibraryList.contains(p);
//                    gameLibraries.removeIf(predicate);
//                    for (User userData : filterByTimeUserList) {
//                        for (GameProfile gameProfile : userData.getGameProfiles()) {
//                            if (gameLibraries.contains(gameProfile.getGameLibrary())) {
//                                count++;
//                                break;
//                            }
//
//                        }
//                    }
//
//                    System.out.println(count + "     count");
//
//                }
//            }
//
//
//            /* Predicate<User> userPredicate = p -> !userList.stream().map(User::getGameProfiles).collect(Collectors.toList()).stream().map(GameProfile::getGameLibrary).collect(Collectors.toList()).contains(user.getGameProfiles().stream().map(GameProfile::getGameLibrary).collect(Collectors.toList()));
//          userList.stream().filter(p -> p.getGameProfiles().forEach(    )
//            userList.removeIf(userPredicate);
//           */
//
//            return userList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    @RequestMapping(value = "updateGameProfiles", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    ResponseEntity<?> updateGameProfiles(@RequestParam String userId, @RequestParam List<String> installedPackageName) {
//
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(updateGameProfileUserProfile(userId, installedPackageName));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//
//    }
//
//
//    @RequestMapping(value = "sendPush", method = RequestMethod.GET)
//    public void getDate() {
//        Long timeMillis = TimeUnit.HOURS.toMillis(new Date().getHours()) + TimeUnit.MINUTES.toMillis(new Date().getMinutes()) + TimeUnit.SECONDS.toMillis(new Date().getSeconds());
//        System.out.println("Hiieee    " + timeMillis);
//
//        //sendPushNotifications();
//    }
//    
    @RequestMapping(value = "/saveAppData", method = RequestMethod.POST)
	public
	@ResponseBody
	Object saveAppData(@RequestBody AppData appData, HttpServletRequest httpSerfvletRequest ) {
		gameProfileService.saveAppData(appData);
		return "App data are logged for userid : ";
	}
	
	@RequestMapping(value = "/addGame", method = RequestMethod.POST)
	public
	@ResponseBody
	Object addGameInfo(@RequestParam("gameImagePath") MultipartFile file, @RequestParam("gameName") String gameName,
			@RequestParam("gamePublisherName") String gamePublisherName, @RequestParam("gamePackageName") String gamePackageName,
			@RequestParam("gameStudioName") String gameStudioName, @RequestParam("ageRating") String ageRating,
			@RequestParam("osType") String osType, @RequestParam("networkType") String networkType,
			@RequestParam("minPlayers") String minPlayers, @RequestParam("maxPlayers") String maxPlayers,
			MultipartHttpServletRequest httpSerfvletRequest ) {
		
		GameData gameData = new GameData();
		gameData.setAgeRating(ageRating);
		gameData.setGameName(gameName);
		gameData.setGameImagePath(file);
		gameData.setGamePublisherName(gamePublisherName);
		gameData.setGamePackageName(gamePackageName);
		gameData.setGameStudioName(gameStudioName);
		gameData.setAgeRating(ageRating);
		gameData.setOsType(Integer.parseInt(osType));
		gameData.setNetworkType(Integer.parseInt(networkType));
		gameData.setMinPlayers(minPlayers);
		gameData.setMaxPlayers(maxPlayers);
		
		gameProfileService.addGameInfo(gameData);
		return "Game Data is inserted : ";
	}
	
	@RequestMapping(value = "/updateUserAvailablity", method = RequestMethod.POST)
	public
	@ResponseBody
	Object updateUserAvailablity(@RequestBody UserAvailablity availablity, HttpServletRequest httpSerfvletRequest ) {
		gameProfileService.updateUserAvailablity(availablity);
		return "App data are logged for userid : ";
	}
	
	@RequestMapping(value = "/getNearByGameList", method = RequestMethod.POST)
	public @ResponseBody
	Object getMutualGameList(@RequestParam("user_id") String userId, HttpServletRequest httpSerfvletRequest) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserDetails(gameProfileService.getMutualGameList(userId));
		return userInfo;
	}
	
	@RequestMapping(value = "/getGameList", method = RequestMethod.POST)
	public @ResponseBody
	Object getGameList(HttpServletRequest httpSerfvletRequest) {
		GameInfo gameInfo = new GameInfo();
		gameInfo.setGameDetails(gameProfileService.getGameList());
		return gameInfo;
	}
	
	@RequestMapping(value = "/editGame", method = RequestMethod.POST)
	public
	@ResponseBody
	Object editGameInfo(@RequestParam("gameImagePath") MultipartFile file, @RequestParam("gameId") String gameId,
			@RequestParam("gameName") String gameName,
			@RequestParam("gamePublisherName") String gamePublisherName, @RequestParam("gamePackageName") String gamePackageName,
			@RequestParam("gameStudioName") String gameStudioName, @RequestParam("ageRating") String ageRating,
			@RequestParam("osType") String osType, @RequestParam("networkType") String networkType,
			@RequestParam("minPlayers") String minPlayers, @RequestParam("maxPlayers") String maxPlayers,
			MultipartHttpServletRequest httpSerfvletRequest ) {
		
		GameData gameData = new GameData();
		gameData.setAgeRating(ageRating);
		gameData.setGameId(Long.parseLong(gameId));
		gameData.setGameName(gameName);
		gameData.setGameImagePath(file);
		gameData.setGamePublisherName(gamePublisherName);
		gameData.setGamePackageName(gamePackageName);
		gameData.setGameStudioName(gameStudioName);
		gameData.setAgeRating(ageRating);
		gameData.setOsType(Integer.parseInt(osType));
		gameData.setNetworkType(Integer.parseInt(networkType));
		gameData.setMinPlayers(minPlayers);
		gameData.setMaxPlayers(maxPlayers);
		
		gameProfileService.editGame(gameData);
		return "Game Data is inserted : ";
	}
	
	@RequestMapping(value = "/deleteGame", method = RequestMethod.POST)
	public @ResponseBody
	Object deleteGame(@RequestParam("gameId") long gameId, HttpServletRequest httpSerfvletRequest) {
		gameProfileService.deleteGame(gameId);
		return "Game is deleted.";
	}
	
	@RequestMapping(value = "/sendConnectionInvite", method = RequestMethod.POST)
	public @ResponseBody
	Object sendConnectionInvite(@RequestBody UserConnectionInfo userConnectionInfo, HttpServletRequest httpSerfvletRequest) {
		gameProfileService.sendConnectionInvite(userConnectionInfo);
		return "Invitation is sent.";
	}
	
	@RequestMapping(value = "/sendRemoteUserInput", method = RequestMethod.POST)
	public @ResponseBody
	Object sendRemoteUserInput(@RequestParam("userId") String userId, @RequestParam("toUserId") long toUserId,
			@RequestParam("bluetoothAddress") String bluetoothAddress,
			@RequestParam("accept") boolean accept, HttpServletRequest httpSerfvletRequest) {
		UserInput userInput = new UserInput();
		userInput.setFromUserId(userId);
		userInput.setToUserId(toUserId);
		userInput.setBluetoothAddress(bluetoothAddress);
		userInput.setAccept(accept);
		
		gameProfileService.sendRemoteUserInput(userInput);
		return "Invitation is sent.";
	}
	
	@RequestMapping(value = "/getMutualGames", method = RequestMethod.POST)
	public @ResponseBody
	Object getMutualGames(@RequestBody UserConnectionInfo userConnectionInfo,
			HttpServletRequest httpSerfvletRequest) {
		ResponseGame resGame = new ResponseGame();
		resGame.setUserDetails(gameProfileService.getMutualGameList(userConnectionInfo.getUserId(), userConnectionInfo.getRemoteUserIds()));
		
		return resGame;
	}
	
	@RequestMapping(value = "/addUserTime", method = RequestMethod.POST)
	public @ResponseBody
	Object addUserTime(@RequestParam("userId") String userId, @RequestParam("fromTime") String fromTime,
			@RequestParam("toTime") String toTime,
			HttpServletRequest httpSerfvletRequest) {
		gameProfileService.addUserAvailabilityTime(userId, fromTime, toTime);
		
		return "Time entry is added.";
	}


    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        System.out.println(b.getTime() + "   " + a.getTime());
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        System.out.println(date.toString());
        cal.setTime(date);
        return cal;
    }

//    public User updateGameProfileUserProfile(String userId, List<String> installedPackageName) {
//
//        try {
//            User user = userDataService.getById(userId);
//            List<GameLibrary> gameLibraries = gameLibraryService.findByPackageNameInAndIsApprovedTrue(installedPackageName);
//            int difference = getDiffYears(new Date(user.getDob()), new Date(System.currentTimeMillis()));
//            System.out.println("Year  " + difference);
//            List<GameLibrary> ageRestrictionsGameLibraries = gameLibraryService.findByAgeRatingLessThan(difference);
//            //  gameLibraries.removeAll(ageRestrictionsGameLibraries);
//
//            List<GameLibrary> tempGameLibrary = new ArrayList<>();
//
//            for (GameLibrary gameLibrary : gameLibraries) {
//                if (ageRestrictionsGameLibraries.contains(gameLibrary)) {
//                    tempGameLibrary.add(gameLibrary);
//                }
//            }
//            gameLibraries.clear();
//            gameLibraries.addAll(tempGameLibrary);
//
//
//            System.out.println("Game Library " + Arrays.toString(gameLibraries.stream().map(GameLibrary::getPackageName).collect(Collectors.toList()).toArray()));
//            System.out.println(" Age Game Library " + Arrays.toString(ageRestrictionsGameLibraries.stream().map(GameLibrary::getPackageName).collect(Collectors.toList()).toArray()));
//
//            for (GameProfile gameProfile : user.getGameProfiles()) {
//                gameProfile.setGameLibrary(null);
//            }
//            user.getGameProfiles().clear();
//            userDataService.save(user);
//            //System.out.println("Game Profile size " + user.getGameProfiles().size() + "   now size  " + userDataService.getById(userId).getGameProfiles().size());
//
//            List<GameProfile> gameProfileList = new ArrayList<>();
//            for (GameLibrary gameLibrary : gameLibraries) {
//                GameProfile profile = new GameProfile();
//                profile.setUserId(user);
//                profile.setGameProfileId(Generator.generateUniqueId());
//                profile.setGameLibrary(gameLibrary);
//                profile.setCreationDate(System.currentTimeMillis());
//                gameProfileList.add(profile);
//            }
//            user.getGameProfiles().addAll(gameProfileList);
//            for (GameProfile gameProfile : gameProfileList)
//                System.out.print("  " + gameProfile.getGameLibrary().getGameName());
//            userDataService.save(user);
////            userDataService.save(user);
//            return user;
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }


}
