package csci310.weatherplanner.weathersource;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import csci310.weatherplanner.weathersource.mock.MockExternalAPI;

public class GooglePlacesAPITest {
	
	private GooglePlacesAPI googlePlacesAPI;
	private String place;
	private ArrayList<String> photos;
	MockExternalAPI api;
	
	@Before
	public void setUp() {
		place = "San Francisco";
		Map<String, String> endpointMap = new HashMap<String,String>();
		endpointMap.put("/findplacefromtext/json", "{\"candidates\":[{\"place_id\":\"ChIJIQBpAG2ahYAR_6128GcTUEo\"}],\"status\":\"OK\"}");
		endpointMap.put("place/details/json", "{\"html_attributions\":[],\"result\":{\"photos\":[{\"height\":442,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/104494000466617447205\\\">Manju Gupta</a>\"],\"photo_reference\":\"CmRaAAAAQzYnpUm4FRR0o5NEv0JRqH9-wjhtxUS-M33-bN_noaz3iKtMtcmPLEPXw9xHFp95dw9j6X5u4pVrBJBbuWurJq_v2jF-w8ahGAi-YT0SVsxYZ_dmh2GOSxm38BgH0o3XEhAOwD2pkPq7eD5Ix_f4iJQqGhSzDX_DeqHcLruPGqJcNsffK-S3JQ\",\"width\":707},{\"height\":1920,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/112361167701064374233\\\">Jishwaan Rakupathi</a>\"],\"photo_reference\":\"CmRaAAAAD_IgZ2vLGARkS5XbQ-H-9PN6KdbSLY5AeMgtLR28l9adMbrReVch6_EFBDOMest3D_zkAcdvseTgOGaZxGZL5pDmnAE1Lf8ONCt_ZJtk1IzDCNIrTTni9Jp66ChuCw_PEhBpc0GsknVcp1KTAC6Q1kSvGhSaq5ozjzYBvAPn4Jt7WLcVT1XevA\",\"width\":2160},{\"height\":485,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/108862076113400282635\\\">J.F. Traveler</a>\"],\"photo_reference\":\"CmRaAAAA7MuoKHonh8UnyXsXtkTC3oKExWz6-4fNnqnt_sRni_Ibc5EpXyfflU_iU_6-R5Gs6dXqYE4xRMZ7YuYWe_8lOohJfdV_RbOYe7zclp8jsZTf2LFFx3VEtzh-iKF_p2-WEhAnzqDa39tI_dhbXGnBC6QXGhT4SWYJFPQ9QHHnFj_bnroXHyq3UA\",\"width\":896},{\"height\":416,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/115055200036247424884\\\">Rosie crockett</a>\"],\"photo_reference\":\"CmRaAAAAoCT56V3gQjtKr6iJxJXisZveqMRq3p-mgzBAk1UDBSgxligcfETY6nh1zL7RCdOD9H-ep2u6hd3fIRB4u3uiSmrE6DnRLNgnpEOHEPaKEd-5m4dSsp0_-MguMpczSCGwEhD2mxP9Ieq_7z1xzKASMBPpGhS2wdSo4kcFnv17PK_xu1D5yNWRLA\",\"width\":620},{\"height\":1216,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/109849026903893201606\\\">Jayasimha Nuggehalli</a>\"],\"photo_reference\":\"CmRaAAAA-XZgzEWQgHQqZYN794w5sX96Zj2qnkmq9jIXP_18o_v3yzgf3IuNIvZrncKSUIY_2UGFzcMxiEwT_nvUMSIGj2L4DbnYIkOFC1sU2Dy0aWPLMnpUBJAhdzb-m8feOSGVEhDcM7Ps0aq6Yq2Zo5Ehf7vYGhRAOib54arXU5sDi6mNs9Ugb61Jmg\",\"width\":2585},{\"height\":1960,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/111288247990567777769\\\">xiaoshan shao</a>\"],\"photo_reference\":\"CmRaAAAAlaZNz61wHdRi_81nBUOkrGLiTEOQQIsJp9bRvUBa8a6MCaY43NSnaklQEraXYXjFzdvQvi1e7XFphU8dv439GkioLWECpa81W6F9NG_ytz8p9AMa4x6ZEbgAQowGriHeEhCT2nk5RkhRkWbuJK4rwLo4GhRJnhPHH3wVKxgULEtpA30zHQOnxg\",\"width\":4032},{\"height\":3024,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/118322769570543258259\\\">Dana JEONG</a>\"],\"photo_reference\":\"CmRaAAAAexikWTP5M4OCAvMVmq2OgjNH7txjRtiDADP4AvK6H62V98-K_Sn8BroQP0r3OxfKz4yqCFL6WtIwfFag6mqHgSCQdgWQDnJMaJQ8Egon59Cx97piUyi4O3WFH7flS0EXEhC_FB7yLNFoU4spVNuRWQZyGhQZ0eZHp0ZNm5gCEjVWPb8NKaSTuA\",\"width\":4032},{\"height\":3744,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/108343056089488449694\\\">Mark Jervis</a>\"],\"photo_reference\":\"CmRaAAAAwKici10OnH6j42R6pADsMlkw-Ri8n8wEI8W9RunihfXjpp73PsNHpkwC_5x2UDNB1La36zSfwn9XxCjpSp0juydD03LR4J6dOvuo8vTSZrrElV4emHiKa8gP2oeWEnjbEhDHaKqS-U7SF7d_aSuS_QxOGhSL5m39mX6p93uBeM1T0hzxdFqlwQ\",\"width\":5616},{\"height\":4032,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/115233017125927916150\\\">Ajaïne</a>\"],\"photo_reference\":\"CmRaAAAAeb37SJd5F7HpTu-oydlKOK4e5AkRO4j4MWcUwh4szS-u4nVHNCkvTUC4BiC6qD8assMP15AAPMOMEhlJlIw1HQhXj2LGZByuxhJRaywRkxdmkPmBuOFyncFrHbXuci0dEhCUMs8Sy9cnd3lB_cDpMu7sGhS3PQi8TRusJtVrp4ZENq24aXx21Q\",\"width\":3024},{\"height\":355,\"html_attributions\":[\"<a href=\\\"https://maps.google.com/maps/contrib/100686858535921818988\\\">the lowsky</a>\"],\"photo_reference\":\"CmRaAAAAlLICKuaZSC8JKcaKK8qwaH-MEcH5hSrc9LZtfJNo-wz1dbe-y1KCJF_P5SlHn7PguFtHszdbs7WfDZzd38zAI4Z-MaEUcsajPoqb5MbbtuVs522L5boT6vAtmM5lNS69EhAD86Ng57n10qh1i_M1LqZwGhQVUlH6HQ0AomxZYSxRY-DoVWpljA\",\"width\":700}]},\"status\":\"OK\"}");
		api = new MockExternalAPI(endpointMap);
		googlePlacesAPI = new GooglePlacesAPI(api);
		photos = new ArrayList<String>();
		photos.add("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRaAAAAQzYnpUm4FRR0o5NEv0JRqH9-wjhtxUS-M33-bN_noaz3iKtMtcmPLEPXw9xHFp95dw9j6X5u4pVrBJBbuWurJq_v2jF-w8ahGAi-YT0SVsxYZ_dmh2GOSxm38BgH0o3XEhAOwD2pkPq7eD5Ix_f4iJQqGhSzDX_DeqHcLruPGqJcNsffK-S3JQ&key=AIzaSyCQPmciWJlie69QLNo-uy-hLTwRLAiOots");
		photos.add("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRaAAAAD_IgZ2vLGARkS5XbQ-H-9PN6KdbSLY5AeMgtLR28l9adMbrReVch6_EFBDOMest3D_zkAcdvseTgOGaZxGZL5pDmnAE1Lf8ONCt_ZJtk1IzDCNIrTTni9Jp66ChuCw_PEhBpc0GsknVcp1KTAC6Q1kSvGhSaq5ozjzYBvAPn4Jt7WLcVT1XevA&key=AIzaSyCQPmciWJlie69QLNo-uy-hLTwRLAiOots");
		photos.add("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CmRaAAAA7MuoKHonh8UnyXsXtkTC3oKExWz6-4fNnqnt_sRni_Ibc5EpXyfflU_iU_6-R5Gs6dXqYE4xRMZ7YuYWe_8lOohJfdV_RbOYe7zclp8jsZTf2LFFx3VEtzh-iKF_p2-WEhAnzqDa39tI_dhbXGnBC6QXGhT4SWYJFPQ9QHHnFj_bnroXHyq3UA&key=AIzaSyCQPmciWJlie69QLNo-uy-hLTwRLAiOots");
	}
	
	@Test
	public void testGetImages() {
		List<String> result = googlePlacesAPI.getImages(place);
		assertNotNull(result);
		assertEquals(photos, result);
	}
}
