import React from 'react';

import './home.styles.scss';

const Home = () => {

  return (
    <div>
      
    <section className="banner" id="home">
        <div className="textBx">
            <h2>Welcome<br /><span>Inventory Management</span></h2>
            <h3>Manage Your Inventory Stress Free</h3>
            <a className="btn">About Us</a>
        </div>
    </section>

    <section className="about" id="about">
        <div className="heading">
            <h2>About Us</h2>
        </div>
        <div className="content">
            <div className="contentBx w50">
                <h3>I'm a Fullstack Web Developer.</h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quisquam, excepturi eveniet ex qui, ea possimus odio sapiente consectetur exercitationem, illum libero? Reprehenderit quis neque ipsam expedita, et porro temporibus qui! Lorem ipsum dolor sit amet consectetur adipisicing elit. Pariatur, quam consectetur. Laborum ipsam blanditiis inventore sunt, rerum minus atque corporis temporibus quia quidem error dolores perferendis illo excepturi saepe amet. <br /><br />Lorem ipsum dolor, sit amet consectetur adipisicing elit. Quam necessitatibus eaque, est suscipit omnis neque ex laboriosam impedit itaque inventore totam esse nisi aut eligendi.</p>
            </div>
            <div className="w50">
                <img src="./images/img1.jpg" alt='good'/>
            </div>
        </div>
    </section>

    <section className="services" id="services">
        <div className="heading white">
            <h2>Our Services</h2>
            <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit.</p>
        </div>

        <div className="content">
            <div className="servicesBx">
                <img src="./images/icon1.png" alt='good'/>
                <h2>Web Design</h2>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Dolorum quod, et libero explicabo alias sapiente expedita eligendi natus cumque! Eligendi asperiores error molestias. Cumque ipsa amet minima, et aperiam molestias.</p>
            </div>

            <div className="servicesBx">
                <img src="./images/icon2.png" alt='good '/>
                <h2>Web Development</h2>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Dolorum quod, et libero explicabo alias sapiente expedita eligendi natus cumque! Eligendi asperiores error molestias. Cumque ipsa amet minima, et aperiam molestias.</p>
            </div>

            <div className="servicesBx">
                <img src="./images/icon3.png" alt='good '/>
                <h2>Android Development</h2>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Dolorum quod, et libero explicabo alias sapiente expedita eligendi natus cumque! Eligendi asperiores error molestias. Cumque ipsa amet minima, et aperiam molestias.</p>
            </div>

            <div className="servicesBx">
                <img src="./images/icon4.png" alt='good '/>
                <h2>Photography</h2>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Dolorum quod, et libero explicabo alias sapiente expedita eligendi natus cumque! Eligendi asperiores error molestias. Cumque ipsa amet minima, et aperiam molestias.</p>
            </div>

            <div className="servicesBx">
                <img src="./images/icon5.png" alt='good '/>
                <h2>Content Writing</h2>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Dolorum quod, et libero explicabo alias sapiente expedita eligendi natus cumque! Eligendi asperiores error molestias. Cumque ipsa amet minima, et aperiam molestias.</p>
            </div>

            <div className="servicesBx">
                <img src="./images/icon6.png" alt='good '/>
                <h2>Video Editing</h2>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Dolorum quod, et libero explicabo alias sapiente expedita eligendi natus cumque! Eligendi asperiores error molestias. Cumque ipsa amet minima, et aperiam molestias.</p>
            </div>

        </div>
    </section>

    

    <section className="testimonial" id="testimonial">

        <div className="heading">
            <h2>Testimonials</h2>
            <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit.</p>
        </div>

        <div className="content">

            <div className="testimonialBx">
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nisi minima voluptatum minus est ducimus cum aperiam optio aut! Quae distinctio ab autem recusandae molestiae corporis error voluptatum ipsam nesciunt perspiciatis. Lorem, ipsum dolor sit amet consectetur adipisicing elit. Minus laboriosam rerum eligendi deleniti soluta, quod ut provident? Voluptatem hic dignissimos quia quis commodi facere culpa quos explicabo, iure modi eligendi.</p>
                <h3>Someone Famous <br /><span>Creative Designer</span></h3>
            </div>

            <div className="testimonialBx">
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nisi minima voluptatum minus est ducimus cum aperiam optio aut! Quae distinctio ab autem recusandae molestiae corporis error voluptatum ipsam nesciunt perspiciatis. Lorem, ipsum dolor sit amet consectetur adipisicing elit. Minus laboriosam rerum eligendi deleniti soluta, quod ut provident? Voluptatem hic dignissimos quia quis commodi facere culpa quos explicabo, iure modi eligendi.</p>
                <h3>Someone Famous <br /><span>Creative Designer</span></h3>
            </div>

        </div>

    </section>

    <section className="contact" id="contact">

        <div className="heading white">
            <h2>Contact Us</h2>
            <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit.</p>
        </div>

        <div className="content">
            <div className="contactInfo">
                <h3>Contact Info</h3>
                <div className="contactInfoBx">

                    <div className="box">
                        <div className="icon">
                            <i className="fa fa-map-marker"></i>
                        </div>
                        <div className="text">
                            <h3>Address</h3>
                            <p>Sanmati Colony, Shegaon Road,<br />Amravati,Maharashtra,<br />444604 </p>
                        </div> 
                    </div>

                    <div className="box">
                        <div className="icon">
                            <i className="fa fa-phone"></i>
                        </div>
                        <div className="text">
                            <h3>Phone</h3>
                            <p>899-916-5653</p>
                        </div> 
                    </div>

                    <div className="box">
                        <div className="icon">
                            <i className="fa fa-envelope-o"></i>
                        </div>
                        <div className="text">
                            <h3>Email</h3>
                            <p>xyz@gmail.com</p>
                        </div> 
                    </div>

                </div>
            </div>
            <div className="formBx">
                <form action="">
                    <h3>Contact Me</h3>
                    <input type="text" name="" placeholder="Full Name" />
                    <input type="email" name="" placeholder="Email" />
                    <textarea placeholder="Your Message"></textarea>
                    <input type="submit" value="Send" />
                </form>
            </div>
        </div>

    </section>

    <section className="copyright">
        <p>Copyright © 2024 .All Right Reserved.</p>
    </section>
  

    </div>
  )
}

export default Home;
