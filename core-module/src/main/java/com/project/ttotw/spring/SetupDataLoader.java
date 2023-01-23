package com.project.ttotw.spring;

import com.project.ttotw.entity.Admin;
import com.project.ttotw.entity.GrapeVarieties;
import com.project.ttotw.enums.Role;
import com.project.ttotw.repository.AdminRepository;
import com.project.ttotw.repository.GrapeVarietiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final AdminRepository adminRepository;
    private final GrapeVarietiesRepository grapeVarietiesRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${setup.data.grape-varieties}")
    private boolean needUpdate;

    private boolean alreadySetup = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //check alreadySetup
        if (alreadySetup) {
            return;
        }

        System.out.println("needUpdate : " + needUpdate);
        //update grapeVarieties
        if (needUpdate) {
            updateGrapeVarieties();
        }

        //create initial admin
        createAdminIfNotFound("admin", "test1234!", Role.ROLE_ADMIN);
        alreadySetup = true;
    }

    Admin createAdminIfNotFound(final String email, final String password, final Role role) {
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin == null) {
            admin = Admin.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(role)
                    .build();
            //save
            adminRepository.save(admin);
        }
        return admin;
    }

    //TODO:: 해당 방식으로 관리되었을 때 데이터가 꼬일 위험이 있음, 추후에는 일반적으로 등록하고 수정하는 방식으로 변경 필요
    void updateGrapeVarieties() {
        List<GrapeVarieties> grapeVarietiesList = new ArrayList<>();
        grapeVarietiesList.add(new GrapeVarieties(1L, "Syrah(Shiraz)", "시라(쉬라즈)", "프랑스 론 지방에서 주로 재배되는 품종으로 프랑스에서는 \'시라\'라고 불리지만 오스트레일리아에서는 \'쉬라즈\'라고 불립니다. 타닌감이 풍부하여 육감적인 맛을 냅니다. 신선한 과일향과 민트, 스파이시한 맛이 특징이며 장기 숙성하면 후추향과 바이올렛, 블랙베리 향이 나는 와인이 됩니다."));
        grapeVarietiesList.add(new GrapeVarieties(2L, "Malbec", "말벡", "껍질이 두껍고 진한 색을 띠며, 폴리페놀 성분이 많아 건강에 좋은 것으로도 유명한 품종입니다. 아르헨티나 와인의 대표 품종으로 예전에는 보르도 지역에서 많이 재배했으나 지금은 보르도에서 블렌딩 품종으로 명맥을 유지하고 있습니다. 어둡고 단단한 타닌을 제공하여 자두와 같은 풍미를 가지며 붉은 색을 넘어 보랏빛에 가까운 와인을 만들 수 있습니다.  열정적이고 강렬한 색감과 깊고 진한 타닌감과 상반되는 부드럽고 실키한 질감이 매력적인 품종입니다."));
        grapeVarietiesList.add(new GrapeVarieties(3L, "Nebbiolo", "네비올로", "이탈리아 와인의 대표작인 바롤로와 바르바레스코를 만드는 품종입니다. 오랜 숙성을 필요로 하는 포도 품종으로 최소한 6년 이상은 숙성시켜야 제대로 맛을 냅니다. 미디엄 바디이지만 무게감이 있고, 섬세한 산도와 풍성한 풍미를 지니고 있으며 첫 맛은 체리와 자두, 두번째는 시나몬과 감초, 세번째는 담배와 가죽 풍미를 느낄 수 있습니다. 오픈해서 바로 마시기보다는 천천히 2-3시간에 걸쳐 잔에서 바뀌는 향을 느끼면서 마시는 것이 좋습니다."));
        grapeVarietiesList.add(new GrapeVarieties(4L, "Carbernet Sauvignon", "카베르네 소비뇽", "고급 레드와인을 제조할 때 사용하는 품종으로 세계적으로 가장 널리 보급되어 있는 레드와인 품종입니다. \'까쇼\' 라는 애칭으로 불리우기도 하는 이 포도는 껍질이 두껍고 씨앗이 큽니다. 까쇼는 파워풀하고 타닌이 강한 맛을 내며, 당도가 낮은 편입니다. 처음에는 떫은 맛이 강하지만 숙성이 될수록 부드러운 맛을 내며, 오래 숙성시킬 수 있다는 장점이 있습니다. 프랑스 보르도 지방의 제 1품종으로, 주로 메를로 품종과 블렌딩하여 만들며 프랑스 메독 지역 와인의 주 품종입니다."));
        grapeVarietiesList.add(new GrapeVarieties(5L, "Zinfandel", "진판델", "미국 캘리포니아의 주력 품종으로 대중적인 저그 와인(Jug Wine)에 주로 사용되었으나, 지금은 고급 레드와인 제조에 사용되고 있습니다. 진판델로 만든 레드와인은 알코올 도수가 높은 편입니다.  주로 시라 품종과 블렌딩합니다. 블랙베리, 딸기, 복숭아잼, 계피, 담배의 캐릭터가 주로 느껴지며 강건한 스타일에 진득한 느낌을 줍니다."));
        grapeVarietiesList.add(new GrapeVarieties(6L, "Merlot", "메를로", "까쇼와 함꼐 보르도의 대표적인 포도 품종으로, 우아하고 섬세한 와인, 달콤하고 높은 알코올과 과일향이 풍부한 와인을 선호하는 분들께 어울리는 와인입니다.  미디엄 바디급의 부드러운 레드와인을 만드는 보르도지방의 제 2품종으로 주로 카베르네 소비뇽과 블렌딩하며, 단 맛이 강해서 카베르네 소비뇽의 거친 맛을 부드럽게 해 줍니다."));
        grapeVarietiesList.add(new GrapeVarieties(7L, "Sangiovese", "산지오베제", "키안티와 브루넬로, 디몬탈치노 등의 레드와인을 생산하는 이탈리아 대표 품종입니다. 체리, 딸기와 허브향이 복합적으로 풍기며, 뒷맛의 여운이 길고 부드러우면서도 화려하여 매혹적인 맛을 냅니다. 산지오베제는 드라이와인으로 생산되고 있으며 양조하여 숙성되면 검붉은 색과 산도가 높고 풍부한 과일향이 특징적인 와인이 됩니다. 시큼한 블랙베리, 라즈베리 향이 나며 허브향, 훈제향, 흙냄새, 감초 등 복합적이며 다양한 향을 가집니다."));
        grapeVarietiesList.add(new GrapeVarieties(8L, "Tempranillo", "템프라니요", "스페인이 자랑하는 대표 품종으로, 블렌딩용으로 주로 사용되지만 단일 품종으로도 훌륭한 와인이 많이 생산됩니다. 버섯과 나무향을 풍기며, 당분 함량이 높고 산도가 낮습니다. 이 품종의 원산지인 스페인 리오하는 '스페인의 보르도' 라는 별명이 붙은 최고의 와인생산지로, 이 지역의 포도는 대체로 품질에 비해 가격이 저렴하여 가격 대비 품질로는 세계 정상급 와인에 속합니다."));
        grapeVarietiesList.add(new GrapeVarieties(9L, "Pinot Noir", "피노 누아", "\'피노\' 라고도 불리우는, 카베르네 소비뇽과 더불어 세계 2대 적포도 품종입니다. 진하지 않은 예쁜 빛깔을 지니고 있으며, 처음에는 과일향을 강하게 풍기지만 숙성시키면 재배한 토양에 따라 향이 제각각이며 그 향이 어떤 품종과도 비교할 수 없을 정도로 고급스럽습니다. 일반적으로 까쇼처럼 타닌이 강한 와인을 선호하는 경우 피노 누아가 싱겁다고 느낄 수도 있지만, 섬세하고 우아하며 화사한 맛을 좋아하는 사람들에게 인기가 많은 품종입니다. 세계 3대 명품 와인 중 하나인 \'로마네 콩띠\' 가 바로 피노 누아로 생산되는 와인입니다."));
        grapeVarietiesList.add(new GrapeVarieties(10L, "Gamay", "가메", "보졸레 와인의 주 원료로 사용되는 품종으로, 진한 체리향과 자두향을 풍깁니다. 와인에서 화려한 꽃밭이 펼쳐진 듯 꽃향기가 난다면 바로 가메입니다. 과일향과 꽃향 등의 아로마가 특징이라 가벼운 음식과 잘 어울리며, 화이트 와인같은 레드 와인으로 가볍고 신선하며 발랄해서 즐거운 기분을 선사하는 품종입니다. 루비의 붉은 빛과 진한 담홍색 등 특유의 아름다운 빛깔로 유명합니다."));
        grapeVarietiesList.add(new GrapeVarieties(11L, "Carménère", "까르미네르", "까르미네르는 칠레 고유의 포도 품종으로 탄닌과 당도가 많은것으로 유명하며 와인을 제조했을때 짙은 루비색의 바디감이 높은 특성을 지니는 것으로 유명합니다. 칠레 천혜의 환경이 만들어낸 독특한 포도품종 까르미네르는 국내에서도 인기가 많으며 지속적으로 판매량이 증가하고 있습니다."));
        grapeVarietiesList.add(new GrapeVarieties(12L, "Sauvignon Blanc", "소비뇽 블랑", "선명하고 상큼한 과일향을 담고 있는 청포도의 싱그러움이 특징인 품종으로 프랑스, 칠레, 미국, 이탈리아 등 세계적인 포도 재배지에서 고루생산되며 가장 깔끔한 와인을 만들어내는 포도 품종이기도 합니다. 세계적으로는 뉴질랜드에서 재배되는 소비뇽 블랑이 높은 평가를 받고 있습니다."));
        grapeVarietiesList.add(new GrapeVarieties(13L, "Chardonnay", "샤르도네", "프랑스, 미국, 호주, 뉴질랜드, 남미 등 전세계적으로 재배되며 가장 많이 생산되기 때문에 거의 모든 화이트와인의 주 재료로 쓰이는 포도품종입니다. 만드는 방식에 따라 맛이 많이 좌우되므로 숙성이 가장 중요한 품종이기도 하며 특유의 산미로 인하여 치즈와 가장 잘 어울리는 와인이 만들어지는 포도입니다."));
        grapeVarietiesList.add(new GrapeVarieties(14L, "Riesling", "리슬링", "독일을 대표하는 포도 품종으로 독일의 라인(Rhein)과 모젤(Mosel) 지방, 프랑스의 알자스(Alsace) 지방, 오스트리아가 유명 산지이며, 전 세계적으로 재배되는 품종입니다. 드라이 와인(Dry wine)에서 스위트 와인(Sweet wine)까지 여러 타입으로 만들 수 있으며, 신선하고 향이 독특하며 포도 자체의 향을 지니고 있습니다. 풍부한 과일 향기와 꽃향기가 돋보이며 특히 풍부한 미네럴 터치가 특징입니다."));
        grapeVarietiesList.add(new GrapeVarieties(15L, "Gewurztraminer", "게뷔르츠트라미너", "독일과 프랑스 알자스(Alsace) 지방, 오스트리아 등에서 리슬링(Riesling)과 함께 재배되고 있는 품종입니다. 게뷔르츠는 스파이시 즉, 향기로운의 뜻이고 트라미너는 포도 품종의 이름인데 이름처럼 세상에서 가장 향기로운 포도이며, 스파이시한 느낌의 독특한 화인을 만드는 화이트 와인 품종입니다. 여러가지 특성이 리슬링과 비슷하지만 리슬링보다는 화려하고 자극적인 향이 특징입니다."));

        //saveAll
        grapeVarietiesRepository.saveAll(grapeVarietiesList);
    }
}
