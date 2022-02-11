

switch(target) {
    case "simpleText" : simpleText(); break;
    case "simpleImage" : simpleImage(); break;
    case "plugImage" : plugImage(); break;
    case "plugProfile" : plugProfile(); break;
}

// 간단한 텍스트 응답
function simpleText() {
    res = {
        "version" : "2.0",
        "template" : {
            "outputs" : [
                {
                    "simpleText" : {
                        "text" : "간단한 텍스트 응답입니다."
                    }
                }
            ]
        }
    }

    return jsonify(res)
}

// 간단한 이미지 응답
function simpleImage() {
    res = {
        "version": "2.0",
        "template": {
            "outputs": [
                {
                    "simpleImage": {
                        "imageUrl": "https://t1.daumcdn.net/friends/prod/category/M001_friends_ryan2.jpg",
                        "altText": "Hello"
                    }
                }
            ]
        }
    }

    return jsonify(res)
}

// 보안이미지전송 플러그인 // 전송받은 이미지를 그대로 다시 전송해줌
function  plugImage() {
    param = request.get_json()
    imagePlug = json.loads(param['action']['params']['imagePlug'])
    secureUrls = imagePlug['secureUrls']
    // urls = secureUrls[5: -1].split(",")
    target = ''

    for(url in urls) {
        target = url

        res = {
            "version": "2.0",
            "template": {
                "outputs": [
                    {
                        "simpleImage": {
                            "imageUrl": target,
                            "altText": "Hello"
                        }
                    }
                ]
            }
        }
    }

    return jsonify(res)
}


// 개인정보이용 플러그인 // 동의 시 정보를 그대로 전송
function plugprofile() {
    param = request.get_json()
    isOk = param['intent']['extra']['reason']['message']
    profilePlug = param['action']['params']['profilePlug']
    profilePlug = json.loads(profilePlug)
    userOtp = profilePlug['otp'].split("/")[4]

    if(isOk) {
        url = 'https://talk-plugin.kakao.com/otp/' + userOtp + '/profile?rest_api_key=f08aa5bbdf47f4beb14856143a6391fe'
        print(url)
        profile = requests.get(url).json()

        res = {
            "version": "2.0",
            "template": {
                "outputs": [
                    {
                        "simpleText": {
                            "text": "=== 승희 개인정보 이용 === nickname : " + str(profile['nickname']) + " phone : " + str(
                                profile['phone_number']) + " app_id" + str(profile['app_user_id'])
                        }
                    }
                ]
            }
        }
    }
    return jsonify(res)
}


// 응용 입력받은 값 이용
function findCenter() {
    param = request.get_json()
    seoulLocal = param['action']['detailParams']['centerName']['origin']
    centerMap = {
        '중구': 'https://map.kakao.com/?urlX=497140&urlY=1129894&urlLevel=3&itemId=12439564&q=서울%20고용복지플러스센터&srcid=12439564&map_type=TYPE_MAP',
        '종로구': 'https://map.kakao.com/?urlX=497140&urlY=1129894&urlLevel=3&itemId=12439564&q=서울%20고용복지플러스센터&srcid=12439564&map_type=TYPE_MAP',
        '동대문구': 'https://map.kakao.com/?urlX=497140&urlY=1129894&urlLevel=3&itemId=12439564&q=서울%20고용복지플러스센터&srcid=12439564&map_type=TYPE_MAP',
        '서초구': 'https://map.kakao.com/?urlX=502424&urlY=1106894&urlLevel=3&itemId=12980695&q=서울서초%20고용센터&srcid=12980695&map_type=TYPE_MAP',
        '강남구': 'https://map.kakao.com/?urlX=511220&urlY=1112502&urlLevel=3&itemId=11441018&q=서울강남%20고용센터&srcid=11441018&map_type=TYPE_MAP',
        '성동구': 'https://map.kakao.com/?urlX=527046&urlY=1109941&urlLevel=3&itemId=9114752&q=서울동부%20고용복지플러스센터&srcid=9114752&map_type=TYPE_MAP',
        '광진구': 'https://map.kakao.com/?urlX=527046&urlY=1109941&urlLevel=3&itemId=9114752&q=서울동부%20고용복지플러스센터&srcid=9114752&map_type=TYPE_MAP',
        '강동구': 'https://map.kakao.com/?urlX=527046&urlY=1109941&urlLevel=3&itemId=9114752&q=서울동부%20고용복지플러스센터&srcid=9114752&map_type=TYPE_MAP',
        '송파구': 'https://map.kakao.com/?urlX=527046&urlY=1109941&urlLevel=3&itemId=9114752&q=서울동부%20고용복지플러스센터&srcid=9114752&map_type=TYPE_MAP',
        '용산구': 'https://map.kakao.com/?urlX=488458&urlY=1122975&urlLevel=3&itemId=11444542&q=서울서부%20고용복지플러스센터&srcid=11444542&map_type=TYPE_MAP',
        '마포구': 'https://map.kakao.com/?urlX=488458&urlY=1122975&urlLevel=3&itemId=11444542&q=서울서부%20고용복지플러스센터&srcid=11444542&map_type=TYPE_MAP',
        '서대문구': 'https://map.kakao.com/?urlX=488458&urlY=1122975&urlLevel=3&itemId=11444542&q=서울서부%20고용복지플러스센터&srcid=11444542&map_type=TYPE_MAP',
        '은평구': 'https://map.kakao.com/?urlX=488458&urlY=1122975&urlLevel=3&itemId=11444542&q=서울서부%20고용복지플러스센터&srcid=11444542&map_type=TYPE_MAP',
        '영등포구': 'https://map.kakao.com/?urlX=476023&urlY=1118093&urlLevel=3&itemId=11137108&q=서울남부%20고용복지플러스센터&srcid=11137108&map_type=TYPE_MAP',
        '양천구': 'https://map.kakao.com/?urlX=476023&urlY=1118093&urlLevel=3&itemId=11137108&q=서울남부%20고용복지플러스센터&srcid=11137108&map_type=TYPE_MAP',
        '강서구': 'https://map.kakao.com/?urlX=467426&urlY=1128760&urlLevel=3&itemId=27293976&q=서울강서%20고용복지플러스센터&srcid=27293976&map_type=TYPE_MAP',
        '중랑구': 'https://map.kakao.com/?urlX=512837&urlY=1153860&urlLevel=3&itemId=9106266&q=서울북부%20고용센터&srcid=9106266&map_type=TYPE_MAP',
        '노원구': 'https://map.kakao.com/?urlX=512837&urlY=1153860&urlLevel=3&itemId=9106266&q=서울북부%20고용센터&srcid=9106266&map_type=TYPE_MAP',
        '강북구': 'https://map.kakao.com/?urlX=512837&urlY=1153860&urlLevel=3&itemId=9106266&q=서울북부%20고용센터&srcid=9106266&map_type=TYPE_MAP',
        '도봉구': 'https://map.kakao.com/?urlX=512837&urlY=1153860&urlLevel=3&itemId=9106266&q=서울북부%20고용센터&srcid=9106266&map_type=TYPE_MAP',
        '성북구': 'https://map.kakao.com/?urlX=512837&urlY=1153860&urlLevel=3&itemId=9106266&q=서울북부%20고용센터&srcid=9106266&map_type=TYPE_MAP',
        '관악구': 'https://map.kakao.com/?urlX=477474&urlY=1107313&urlLevel=3&itemId=10497267&q=서울관악%20고용복지플러스센터&srcid=10497267&map_type=TYPE_MAP',
        '구로구': 'https://map.kakao.com/?urlX=477474&urlY=1107313&urlLevel=3&itemId=10497267&q=서울관악%20고용복지플러스센터&srcid=10497267&map_type=TYPE_MAP',
        '금천구': 'https://map.kakao.com/?urlX=477474&urlY=1107313&urlLevel=3&itemId=10497267&q=서울관악%20고용복지플러스센터&srcid=10497267&map_type=TYPE_MAP',
        '동작구': 'https://map.kakao.com/?urlX=477474&urlY=1107313&urlLevel=3&itemId=10497267&q=서울관악%20고용복지플러스센터&srcid=10497267&map_type=TYPE_MAP'
    }

    res = {}

    if (seoulLocal in centerMap) {
        url = centerMap[seoulLocal]
        res = {
            "version": "2.0",
            "template": {
                "outputs": [
                    {
                        "basicCard": {
                            "title": "검색한 고용센터 결과",
                            "buttons": [
                                {
                                    "action": "webLink",
                                    "label": "고용센터 위치 보기",
                                    "webLinkUrl": url
                                }
                            ]
                        }
                    }
                ]
            }
        }
    } else {
        res = {
            "version": "2.0",
            "template": {
                "outputs": [
                    {
                        "simpleText": {
                            "text": "입력하신 지역에는 고용센터가 없습니다."
                        }
                    }
                ]
            }
        }
    }

    return jsonify(res)
}


// 응용 현재위치 이용해서 ...
function findGPS() {
    // param = request.get_json()
    // seoulLocal = param['action']['detailParams']['centerName']['origin']
    userGPS = '500000,1100000'.split(",")
    userUrl = 'https://map.kakao.com/?urlX=' + userGPS[0] + '&urlY=' + userGPS[1]
    print(userUrl)
    html = requests.get(userUrl)
    soup = BeautifulSoup(html, "html.parser")
    userLocal = soup.select_one('a#localInfo.map.county.selectBox')
    print("===")
    print(userLocal)
    print("===")

    // https://map.kakao.com/?urlX=500000&urlY=1100000
    centerMap = {
        '중구': 'https://map.kakao.com/?urlX=497140&urlY=1129894&urlLevel=3&itemId=12439564&q=서울%20고용복지플러스센터&srcid=12439564&map_type=TYPE_MAP',
        '종로구': 'https://map.kakao.com/?urlX=497140&urlY=1129894&urlLevel=3&itemId=12439564&q=서울%20고용복지플러스센터&srcid=12439564&map_type=TYPE_MAP',
        '동대문구': 'https://map.kakao.com/?urlX=497140&urlY=1129894&urlLevel=3&itemId=12439564&q=서울%20고용복지플러스센터&srcid=12439564&map_type=TYPE_MAP',
        '서초구': 'https://map.kakao.com/?urlX=502424&urlY=1106894&urlLevel=3&itemId=12980695&q=서울서초%20고용센터&srcid=12980695&map_type=TYPE_MAP',
        '강남구': 'https://map.kakao.com/?urlX=511220&urlY=1112502&urlLevel=3&itemId=11441018&q=서울강남%20고용센터&srcid=11441018&map_type=TYPE_MAP',
        '성동구': 'https://map.kakao.com/?urlX=527046&urlY=1109941&urlLevel=3&itemId=9114752&q=서울동부%20고용복지플러스센터&srcid=9114752&map_type=TYPE_MAP',
        '광진구': 'https://map.kakao.com/?urlX=527046&urlY=1109941&urlLevel=3&itemId=9114752&q=서울동부%20고용복지플러스센터&srcid=9114752&map_type=TYPE_MAP',
        '강동구': 'https://map.kakao.com/?urlX=527046&urlY=1109941&urlLevel=3&itemId=9114752&q=서울동부%20고용복지플러스센터&srcid=9114752&map_type=TYPE_MAP',
        '송파구': 'https://map.kakao.com/?urlX=527046&urlY=1109941&urlLevel=3&itemId=9114752&q=서울동부%20고용복지플러스센터&srcid=9114752&map_type=TYPE_MAP',
        '용산구': 'https://map.kakao.com/?urlX=488458&urlY=1122975&urlLevel=3&itemId=11444542&q=서울서부%20고용복지플러스센터&srcid=11444542&map_type=TYPE_MAP',
        '마포구': 'https://map.kakao.com/?urlX=488458&urlY=1122975&urlLevel=3&itemId=11444542&q=서울서부%20고용복지플러스센터&srcid=11444542&map_type=TYPE_MAP',
        '서대문구': 'https://map.kakao.com/?urlX=488458&urlY=1122975&urlLevel=3&itemId=11444542&q=서울서부%20고용복지플러스센터&srcid=11444542&map_type=TYPE_MAP',
        '은평구': 'https://map.kakao.com/?urlX=488458&urlY=1122975&urlLevel=3&itemId=11444542&q=서울서부%20고용복지플러스센터&srcid=11444542&map_type=TYPE_MAP',
        '영등포구': 'https://map.kakao.com/?urlX=476023&urlY=1118093&urlLevel=3&itemId=11137108&q=서울남부%20고용복지플러스센터&srcid=11137108&map_type=TYPE_MAP',
        '양천구': 'https://map.kakao.com/?urlX=476023&urlY=1118093&urlLevel=3&itemId=11137108&q=서울남부%20고용복지플러스센터&srcid=11137108&map_type=TYPE_MAP',
        '강서구': 'https://map.kakao.com/?urlX=467426&urlY=1128760&urlLevel=3&itemId=27293976&q=서울강서%20고용복지플러스센터&srcid=27293976&map_type=TYPE_MAP',
        '중랑구': 'https://map.kakao.com/?urlX=512837&urlY=1153860&urlLevel=3&itemId=9106266&q=서울북부%20고용센터&srcid=9106266&map_type=TYPE_MAP',
        '노원구': 'https://map.kakao.com/?urlX=512837&urlY=1153860&urlLevel=3&itemId=9106266&q=서울북부%20고용센터&srcid=9106266&map_type=TYPE_MAP',
        '강북구': 'https://map.kakao.com/?urlX=512837&urlY=1153860&urlLevel=3&itemId=9106266&q=서울북부%20고용센터&srcid=9106266&map_type=TYPE_MAP',
        '도봉구': 'https://map.kakao.com/?urlX=512837&urlY=1153860&urlLevel=3&itemId=9106266&q=서울북부%20고용센터&srcid=9106266&map_type=TYPE_MAP',
        '성북구': 'https://map.kakao.com/?urlX=512837&urlY=1153860&urlLevel=3&itemId=9106266&q=서울북부%20고용센터&srcid=9106266&map_type=TYPE_MAP',
        '관악구': 'https://map.kakao.com/?urlX=477474&urlY=1107313&urlLevel=3&itemId=10497267&q=서울관악%20고용복지플러스센터&srcid=10497267&map_type=TYPE_MAP',
        '구로구': 'https://map.kakao.com/?urlX=477474&urlY=1107313&urlLevel=3&itemId=10497267&q=서울관악%20고용복지플러스센터&srcid=10497267&map_type=TYPE_MAP',
        '금천구': 'https://map.kakao.com/?urlX=477474&urlY=1107313&urlLevel=3&itemId=10497267&q=서울관악%20고용복지플러스센터&srcid=10497267&map_type=TYPE_MAP',
        '동작구': 'https://map.kakao.com/?urlX=477474&urlY=1107313&urlLevel=3&itemId=10497267&q=서울관악%20고용복지플러스센터&srcid=10497267&map_type=TYPE_MAP'
    }

    res = {}

    if(userLocal in centerMap) {
        url = centerMap[userLocal]
        res = {
            "version": "2.0",
            "template": {
                "outputs": [
                    {
                        "basicCard": {
                            "title": "검색한 고용센터 결과",
                            "buttons": [
                                {
                                    "action": "webLink",
                                    "label": "고용센터 위치 보기",
                                    "webLinkUrl": url
                                }
                            ]
                        }
                    }
                ]
            }
        }
    }
}