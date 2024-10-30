<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="layout/header.jsp"%>
	<h1>${principal.username}님의 정보</h1>
	<c:if test="${not empty logoutMsg}">
	 <div class="alert alert-success">${logoutMsg}</div>
	</c:if>
<div class="shadow-xl mt-4 bg-gradient-to-br from-gray-100 to-gray-200 text-gray-900 rounded-2xl text-lg flex flex-col gap-4 pb-4">
    <h2 class="text-center font-semibold pt-4">계산결과</h2>
    <div class="text-center">
        <hr class="mx-4 border-gray-400">
        <div class="my-2">
            <div class="text-sm">기초 대사량</div>
            <div><span class="text-xl font-bold">${userbody.BMR}</span> kcal</div>
        </div>
        <hr class="mx-4 border-gray-400">
        <div class="my-2">
            <div class="py-4 mx-4 border-gray-600 border-2">
                <div class="text-sm">운동량을 고려한 총 대사량</div>
                <div><span class="text-3xl font-bold" id="userResult">${userbody.total}</span> kcal</div>
            </div>
        </div>
    </div>

    <hr>
    
    <div class="flex text-center">
        <h2 class="font-bold ml-4 text-xl bg-gray-800 text-gray-200 w-fit px-2 py-1">하루 권장 섭취량</h2>
    </div>
    
	 <div class="justify-between text-center flex mx-4" role="tablist" aria-orientation="horizontal">
	   <button class="btn w-full font-semibold text-xl border-2 border-transparent outline-none" id="weight-loss-button"
	 	role="tab" type="button" aria-selected="false" tabindex="-1" aria-controls="weight-loss-panel">
	    체중 감소
	   </button>
       <button class="btn w-full font-semibold text-xl border-2 border-transparent outline-none" id="weight-maintenance-button" 
        role="tab" type="button" aria-selected="false" tabindex="0" aria-controls="weight-maintenance-panel" >
        체중 유지
       </button>
	   <button class="btn w-full font-semibold text-xl border-2 border-transparent outline-none" id="bulk-up-button" 
	 	role="tab" type="button" aria-selected="false" tabindex="-1" aria-controls="bulk-up-panel">
	    벌크업
	   </button>
	</div>

	<div id="weight-loss-panel" class="tab-panel" role="tabpanel" style="display: none;">
	      <div>
            <div class="mx-4 mt-2 text-xs bg-gray-300 py-1 px-2">
                체중 감소가 목표일때는 <br>총 대사량에서 -500kcal로 섭취하셔야 합니다.
            </div>
            <div class="py-4 mt-2 mx-4 border-gray-600 text-center border-2">
                <div class="text-sm">목표 섭취 칼로리</div>
                <div><span class="text-3xl font-bold" id="target-calorie-loss"></span> kcal</div>
            </div>
            <div class="mt-2 w-full flex text-center justify-between px-4 gap-x-2">
                <div class="w-full">
                    <div class="text-base font-medium">기본</div>
                    <div class="text-xs font-light">탄단지 5:3:2</div>
                </div>
            </div>
	        <div class="relative text-base font-semibold mt-2 text-center">
	            <div class="my-4">
	                <div class="text-sm">탄수화물</div>
	                <div><span class="text-3xl font-bold" id="loss-carbon"></span>g</div>
	            </div>
	            <hr class="mx-4 border-gray-400">
	            <div class="my-4">
	                <div class="text-sm">단백질</div>
	                <div>
	                    <span class="text-3xl font-bold" id="loss-protein"></span>g
	                    <div class="flex justify-center items-center font-light">
	                        (<div class="font-extralight">
	                            <details>
	                                <div class="absolute left-2 text-xs translate-x-1/4 w-full items-center">
	                                    <div class="bg-gray-700/20 w-fit px-2 py-1 rounded-lg">닭가슴살 100g에<br> 22.9g의 단백질이 있습니다.</div>
	                                </div>
	                            </details>
	                        </div>
		                        <div class="text-xs font-light">닭가슴살x
		                        <span class="text-3xl font-bold" id="chicken-count-loss">0</span>개</div>
		                    </div>
			                </div>
			            </div>
			            <hr class="mx-4 border-gray-400">
			            <div class="my-4">
			                <div class="text-sm">지방</div>
			                <div><span class="text-3xl font-bold" id="loss-fat"></span>g</div>
			            </div>
			            <hr class="mx-4 border-gray-400">
			        </div>
			    </div>
			</div>
			
	
	<div id="weight-maintenance-panel" class="tab-panel" role="tabpanel" style="display: block;"> 
	    <div>
	        <div class="mx-4 mt-2 text-xs bg-gray-300 py-1 px-2">
	            체중 유지를 위해서는 <br>총 대사량만큼 섭취하셔야 합니다.
	        </div>
	        <div class="py-4 mt-2 mx-4 border-gray-600 text-center border-2">
	            <div class="text-sm">목표 섭취 칼로리</div>
	            <div><span class="text-3xl font-bold" id="target-calorie-maintenance"></span> kcal</div>
	        </div>
	        <div class="mt-2 w-full flex text-center justify-between px-4 gap-x-2">
	            <div class="w-full">
	                <div class="text-base font-medium">기본</div>
	                <div class="text-xs font-light">탄단지 5:3:2</div>
	            </div>
	        </div>
	        <div class="relative text-base font-semibold mt-2 text-center">
	            <div class="my-4">
	                <div class="text-sm">탄수화물</div>
	                <div><span class="text-3xl font-bold" id="maintenance-carbon"></span>g</div>
	            </div>
	            <hr class="mx-4 border-gray-400">
	            <div class="my-4">
	                <div class="text-sm">단백질</div>
	                <div>
	                    <span class="text-3xl font-bold" id="maintenance-protein"></span>g
	                    <div class="flex justify-center items-center font-light">
	                        (<div class="font-extralight">
	                            <details>
	                                <div class="absolute left-2 text-xs translate-x-1/4 w-full items-center">
	                                    <div class="bg-gray-700/20 w-fit px-2 py-1 rounded-lg">닭가슴살 100g에<br> 22.9g의 단백질이 있습니다.</div>
	                                </div>
	                            </details>
	                        </div>
	                       <div class="text-xs font-light">닭가슴살x
			                        <span class="text-3xl font-bold" id="chicken-count-maintenance">0</span>개</div>
			                    </div>
			                </div>
			            </div>
			            <hr class="mx-4 border-gray-400">
			            <div class="my-4">
			                <div class="text-sm">지방</div>
			                <div><span class="text-3xl font-bold" id="maintenance-fat"></span>g</div>
			            </div>
			            <hr class="mx-4 border-gray-400">
			        </div>
			    </div>
			</div>


		
	<div id="bulk-up-panel" class="tab-panel" role="tabpanel" style="display: none;">
	       <div>
            <div class="mx-4 mt-2 text-xs bg-gray-300 py-1 px-2">
                벌크업을 위해서는 <br>총 대사량보다 10% 추가 섭취하셔야 합니다.
            </div>
              <div class="py-4 mt-2 mx-4 border-gray-600 text-center border-2">
                <div class="text-sm">목표 섭취 칼로리</div>
                <div><span class="text-3xl font-bold" id="target-calorie-bulk"></span> kcal</div>
            </div>
            <div class="mt-2 w-full flex text-center justify-between px-4 gap-x-2">
                <div class="w-full">
                    <div class="text-base font-medium">기본</div>
                    <div class="text-xs font-light">탄단지 5:3:2</div>
                </div>
            </div>
		   <div class="relative text-base font-semibold mt-2 text-center">
			            <div class="my-4">
			                <div class="text-sm">탄수화물</div>
			                <div><span class="text-3xl font-bold" id="bulk-up-carbon"></span>g</div>
			            </div>
			            <hr class="mx-4 border-gray-400">
			            <div class="my-4">
			                <div class="text-sm">단백질</div>
			                <div>
			                    <span class="text-3xl font-bold" id="bulk-up-protein"></span>g
			                    <div class="flex justify-center items-center font-light">
			                        (<div class="font-extralight">
			                            <details>
			                                <div class="absolute left-2 text-xs translate-x-1/4 w-full items-center">
			                                    <div class="bg-gray-700/20 w-fit px-2 py-1 rounded-lg">닭가슴살 100g에<br> 22.9g의 단백질이 있습니다.</div>
			                                </div>
			                            </details>
			                        </div>
			                        <div class="text-xs font-light">닭가슴살x
			                        <span class="text-3xl font-bold" id="chicken-count-bulk">0</span>개</div>
			                    </div>
			                </div>
			            </div>
			            <hr class="mx-4 border-gray-400">
			            <div class="my-4">
			                <div class="text-sm">지방</div>
			                <div><span class="text-3xl font-bold" id="bulk-up-fat"></span>g</div>
			            </div>
			            <hr class="mx-4 border-gray-400">
			        </div>
			    </div>
			</div>

		
    <div class="text-center">
    <h2 class="font-bold ml-4 text-xl bg-gray-800 text-gray-200 w-fit px-2 py-1">권장 유산소 강도</h2>
    <p class="pb-4 ml-4 text-sm">
        체력수준에 맞는 MFO(Maxtimal Fat Oxidation)<br>
        지방이 가장 잘 타는 구간입니다.<br>
        추천 심박수로 40분 이상 유산소 운동을 추천합니다.
    </p>
    </div>
    <div class="text-center mb-8">
        <hr class="mx-4 border-gray-400">
        <div class="my-2">
            <div class="text-sm">추천 심박수 범위</div>
            <div>
                <span class="text-3xl font-bold">136<span class="text-sm">bpm</span> ~ 149<span class="text-sm">bpm</span></span>
            </div>
        </div>
        <hr class="mx-4 border-gray-400">
    </div>
</div>

<script src="js/userbody.js"></script>
<%@ include file="layout/footer.jsp"%>