	
				<!--Footer file Start-->
			</main><!-- end main body -->
            <!-- Footer -->
			<footer class="w3-black w3-center">
  				<h5>Find Us On</h5>
  					<div class="w3-xlarge w3-padding-16">
    				<i class="fa fa-facebook-official w3-hover-opacity"></i>
    				<i class="fa fa-instagram w3-hover-opacity"></i>
    				<i class="fa fa-snapchat w3-hover-opacity"></i>
    				<i class="fa fa-pinterest-p w3-hover-opacity"></i>
    				<i class="fa fa-twitter w3-hover-opacity"></i>
    				<i class="fa fa-linkedin w3-hover-opacity"></i>
  				</div>
			</footer><!--Footer Area End-->
        </div> <!-- end wrapper -->
        <?php
        echo $script->buildJs();
        $txtStorage = new txtStorage();
        $txtStorage->errors();
        ?>
    </body>
</html>