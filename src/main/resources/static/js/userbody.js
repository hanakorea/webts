const userBodyObject = {
    init: function() { 
        $("#btn-cal").on("click", (e) => {
            e.preventDefault();
            this.cal();
        });

        $('button[role="tab"]').on('click', (e) => {
            this.selectTab(e.currentTarget);
        });

		const $defaultButton = $('#weight-maintenance-button');
		        this.selectTab($defaultButton[0]); // 기본적으로 체중 유지 탭 선택
	  },

    cal: function() {
        const userBody = {
            gender: $("#gender_cal").val(),
            weight: $("#weight").val(),
            height: $("#height").val(),
            age: $("#age").val(),
            active: $("input[name='active']:checked").val()
        };

        if (!userBody.weight || !userBody.height || !userBody.age || !userBody.active) {
            alert("모든 정보를 입력해 주세요");
            return;
        }

        console.log("보내는 데이터:", userBody); 

        $.ajax({
            type: "POST",
            url: "/usercal",
            data: JSON.stringify(userBody),
            contentType: "application/json;charset=UTF-8"
        })
        .done(function(response) {
            alert(response.data);
            if (!confirm("메인으로 이동하시겠습니까?")) {
                return;
            }
            location.href = "/";
        })
        .fail(function(error) {
            alert("오류가 발생했습니다: " + (error.responseJSON ? error.responseJSON.message : "알 수 없는 오류"));
            console.log(error);
        });
    },

	selectTab: function(button) {
	    const $buttons = $('button[role="tab"]');
	    const $panels = $('[role="tabpanel"]');

	    $buttons.attr('aria-selected', 'false').css({
	        backgroundColor: '',
	        color: ''
	    });

	    $(button).attr('aria-selected', 'true').css({
	        backgroundColor: 'black',
	        color: 'white'
	    });

	    $panels.hide().attr('aria-hidden', 'true');

	    const selectedPanelId = $(button).attr('aria-controls');
	    const $selectedPanel = $('#' + selectedPanelId);
	    if ($selectedPanel.length) {
	        $selectedPanel.show().attr('aria-hidden', 'false');

	        // 목표 섭취 칼로리 계산
	        const totalKcal = parseInt($('#userResult').text());

	        let targetCalories;
	        let chickenCount;

	        if (selectedPanelId === "weight-loss-panel") {
	            targetCalories = totalKcal - 500; // 체중 감소
	            chickenCount = (targetCalories * 0.3 / 22.9).toFixed(2); // 단백질 30%를 닭가슴살로
	            $('#target-calorie-loss').text(targetCalories);
	            $('#chicken-count-loss').text(chickenCount);
	            $('#loss-carbon').text(Math.round(targetCalories * 0.5 / 4));
	            $('#loss-protein').text(Math.round(targetCalories * 0.3 / 4));
	            $('#loss-fat').text(Math.round(targetCalories * 0.2 / 9));
	        } else if (selectedPanelId === "weight-maintenance-panel") {
	            targetCalories = totalKcal; // 체중 유지
	            chickenCount = (targetCalories * 0.3 / 22.9).toFixed(2); // 단백질 30%를 닭가슴살로
	            $('#target-calorie-maintenance').text(targetCalories);
	            $('#chicken-count-maintenance').text(chickenCount);
	            $('#maintenance-carbon').text(Math.round(totalKcal * 0.5 / 4));
	            $('#maintenance-protein').text(Math.round(totalKcal * 0.3 / 4));
	            $('#maintenance-fat').text(Math.round(totalKcal * 0.2 / 9));
	        } else if (selectedPanelId === "bulk-up-panel") {
	            targetCalories = Math.round(totalKcal * 1.1);  // 벌크업
	            chickenCount = (targetCalories * 0.3 / 22.9).toFixed(2); // 단백질 30%를 닭가슴살로
	            $('#target-calorie-bulk').text(targetCalories);
	            $('#chicken-count-bulk').text(chickenCount);
	            $('#bulk-up-carbon').text(Math.round(totalKcal * 1.1 * 0.5 / 4));
	            $('#bulk-up-protein').text(Math.round(totalKcal * 1.1 * 0.3 / 4));
	            $('#bulk-up-fat').text(Math.round(totalKcal * 1.1 * 0.2 / 9));
	        }
	    }
	}

};

userBodyObject.init();

$(document).ready(function() {
    const userResult = $('#userResult').text();
    const totalKcal = parseInt(userResult);
});
